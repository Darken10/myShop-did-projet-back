package com.did.MyShop.auth;


import com.did.MyShop.DTO.user.UserRequest;
import com.did.MyShop.DTO.user.UserResponse;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.config.JwtService;
import com.did.MyShop.entities.User.ResetPasswordJeton;
import com.did.MyShop.entities.User.Token;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusUserEnum;
import com.did.MyShop.enums.TokenType;
import com.did.MyShop.mappers.user.UserMapper;
import com.did.MyShop.repositories.users.ResetPasswordJetonRepository;
import com.did.MyShop.repositories.users.RoleRepository;
import com.did.MyShop.repositories.users.TokenRepository;
import com.did.MyShop.repositories.users.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RoleRepository roleRepository;
  private final UserRepository personnelRepository;
  private final ResetPasswordJetonRepository resetPasswordJetonRepository;
  private final SendEmail sendEmail;

  @Value("${password}")
  private  String password ;

  public UserResponse register(UserRequest request) {
    var existingUser = repository.findUserByMatriculeOrEmailOrPhoneNumber(request.matricule(),request.email(),request.email());
    if (existingUser.isPresent()) {
      throw new RessourceNotFoundException("Un utilisateur avec cet matricule,ou cet email,ou cet numéro de Telephone existe deja");
    }
    var user = User.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .matricule(request.matricule())
            .email(request.email())
            .password(passwordEncoder.encode(password))
            .phoneNumber(request.phoneNumber())
            .genre(request.genre())
            .dateNaissance(request.dateNaissance())
            .roles(new HashSet<>())
            .status(StatusUserEnum.EN_ATTENTE)
            .build();
    request.rolesId()
            .forEach(roleId -> user.addRole(roleRepository.findById(roleId).orElseThrow(() -> new RessourceNotFoundException("Aucun role associer a cet identifiant"))));
    var savedUser = repository.save(user);
    return UserMapper.toUserResponse(savedUser);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          request.matricule(),
                          request.password()
                  )
          );
      } catch (AuthenticationException e) {
          throw new RessourceNotFoundException(e.getMessage());
      }
    var user = repository.findByMatricule(request.matricule())
            .orElseThrow(() -> new RessourceNotFoundException("Aucun utilisateur trouver"));
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return new AuthenticationResponse(
            jwtToken,
            refreshToken,
            UserMapper.toUserResponse(user)
    );
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByMatricule(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = new AuthenticationResponse(
                accessToken,
                refreshToken,
                UserMapper.toUserResponse(user)
        );
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }



  public ResetPasswordJeton createResetPasswordJeton(ResetPasswordCredentialRecord request) throws MessagingException {
    var per = personnelRepository.findUserByEmailAndMatricule(request.email(),request.matricule()).orElseThrow(()->new RessourceNotFoundException("user introuvable"));
   /* List<ResetPasswordJeton> resetPasswordJetons = resetPasswordJetonRepository.findAllByUser(per);*/
    per.getResetPasswords().forEach((resetPasswordJeton) ->{
      resetPasswordJeton.setIsExpire(true);
      resetPasswordJeton.setIsActive(false);
      System.out.println(resetPasswordJeton.getJeton());
    });

    UUID jeton = UUID.randomUUID();
    ResetPasswordJeton resetPasswordJeton =  ResetPasswordJeton
            .builder()
            .user(per)
            .jeton(jeton)
            .isActive(true)
            .isExpire(false)
            .build();

    try {
      sendEmail.sendResetPasswordEmail(per.getEmail(),request.url()+"?jeton="+jeton);
      sendEmail.sendEmail(per.getEmail(),"Votre jeton de reset password", "Votre Lien de recuperation est : " + request.url()+"?jeton="+jeton);
    } catch (MessagingException e) {
        throw new RessourceNotFoundException("Le Mail n'a pas pu etre envoyer"+ e.getMessage());
    }

    return resetPasswordJetonRepository.save(resetPasswordJeton);
  }

  public Optional<ResetPasswordJeton> getResetPasswordJeton(String j){
    UUID jeton = UUID.fromString(j);
    return resetPasswordJetonRepository.findByJeton(jeton);
  }

  public UserResponse changePassword(NewPasswordCredential rq){
    var resetPwd = this.getResetPasswordJeton(rq.jeton()).orElseThrow();
    User personnel = resetPwd.getUser();
    System.out.println(personnel.getEmail());
    System.out.println(rq.password());
    personnel.setStatus(StatusUserEnum.ACTIVE);
    personnel.setPassword(passwordEncoder.encode(rq.password()));
    resetPwd.setIsActive(false);
    resetPasswordJetonRepository.save(resetPwd);

    return UserMapper.toUserResponse(personnelRepository.save(personnel));
  }


}
