package com.did.MyShop.auth;

import com.did.MyShop.DTO.user.UserRequest;
import com.did.MyShop.DTO.user.UserResponse;
import com.did.MyShop.entities.User.ResetPasswordJeton;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;


  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(
      @Valid @RequestBody UserRequest request
  ) throws MessagingException {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @Valid @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @PostMapping("/reset-password")
  public Map<String,Boolean> storeJeton(
          @RequestBody ResetPasswordCredentialRecord request
  ) throws MessagingException {

    return service.resetPwd(request);
  }

  @GetMapping("/reset-password/{jeton}")
  public Map<String,Boolean> storeJeton(
          @PathVariable String jeton
  ) {
    ResetPasswordJeton rpj = service.getResetPasswordJeton(jeton).orElse(null);
    Map<String,Boolean> val = new HashMap<>();
    val.put("isActive", rpj != null && rpj.getIsActive());
    val.put("isExpired",  rpj != null && rpj.getIsExpire() );
    return val;
  }

  @PostMapping("/reset-password/new-password")
  public UserResponse storeJeton(
          @RequestBody NewPasswordCredential request
  ) {
    return service.changePassword(request);
  }



  @PostMapping("/logout")
  public ResponseEntity<Map<String,String>> logout(@RequestHeader("Authorization") String token) {
    if (token.startsWith("Bearer ")) {
      token = token.substring(7);
    }
    Map<String,String> map = new HashMap<>();
    map.put("status", "success");
    map.put("message" ,"Déconnexion réussie, token invalide !");
    service.blacklistToken(token);
    return ResponseEntity.ok(map);
  }


}
