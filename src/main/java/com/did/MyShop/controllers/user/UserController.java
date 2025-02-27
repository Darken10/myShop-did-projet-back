package com.did.MyShop.controllers.user;

import com.did.MyShop.DTO.commande.CommandeResponse;
import com.did.MyShop.DTO.user.UserRequest;
import com.did.MyShop.DTO.user.UserResponse;
import com.did.MyShop.auth.AuthenticationService;
import com.did.MyShop.auth.ChangePasswordRequest;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.mappers.user.UserMapper;
import com.did.MyShop.services.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/auth/user")
@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationService service;

    @GetMapping
    public UserResponse getConnectedUser(Principal connectedUser) {
        return userService.getUser(connectedUser);
    }

    @GetMapping("all")
    public List<UserResponse> index(){
        return userService.All();
    }





    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRequest request
    ) throws MessagingException {
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/{id}")
    public UserResponse show(
            @PathVariable("id") Long personnelId
    ){
        return userService.find(personnelId);
    }


    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable("id") Long personnelId,
            @Valid @RequestBody UserRequest userRequest
    ){
        return userService.update(personnelId,userRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Long personnelId
    ){
        userService.delete(personnelId);
    }

    @PatchMapping("/change-password")
    public UserResponse resetPassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        return userService.changePassword(request, connectedUser);
    }




}
