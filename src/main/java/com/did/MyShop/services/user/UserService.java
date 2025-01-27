package com.did.MyShop.services.user;

import com.did.MyShop.DTO.user.UserRequest;
import com.did.MyShop.DTO.user.UserResponse;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.auth.ChangePasswordRequest;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusUserEnum;
import com.did.MyShop.mappers.user.UserMapper;
import com.did.MyShop.repositories.users.RoleRepository;
import com.did.MyShop.repositories.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private   final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String password = "password";

    public List<UserResponse> All(){
        return userRepository.findAll().stream().map(UserMapper::toUserResponse).toList();
    }

    public  UserResponse find(Long id) {
        var per = userRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("user introuvable"));
        return UserMapper.toUserResponse(per) ;
    }

    public UserResponse save(UserRequest userRequest){
        User per = UserMapper.toUser(userRequest);
        per.setPassword(UserService.password);

        userRequest.rolesId()
                .forEach(roleId -> {
                    per.addRole(roleRepository.findById(roleId).orElse(null));
                });
        return UserMapper.toUserResponse(userRepository.save(per));
    }

    public UserResponse update(Long id,UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("user introuvable"));

        if (!userRequest.rolesId().isEmpty()) {
            userRequest.rolesId()
                    .forEach(roleId ->{
                        user.addRole(roleRepository.findById(roleId).orElseThrow(()->new RessourceNotFoundException("Role introuvable")));
                    });
        }
        return UserMapper.toUserResponse(userRepository.save(user));
    }

    public void delete(Long id){
        var per=userRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("user introuvable"));
        userRepository.delete(per);
    }

    public UserResponse changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new RessourceNotFoundException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.newPassword().equals(request.confirmationPassword())) {
            throw new RessourceNotFoundException("Password are not the same");
        }

        // update the password
        user.setStatus(StatusUserEnum.ACTIVE);
        user.setPassword(passwordEncoder.encode(request.newPassword()));

        // save the new password
        userRepository.save(user);
        return UserMapper.toUserResponse(user);
    }

    public UserResponse getUser(Principal connectedUser) {
        return UserMapper.toUserResponse((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
    }
}

