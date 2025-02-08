package com.did.MyShop.controllers.user;

import com.did.MyShop.DTO.commande.CommandeResponse;
import com.did.MyShop.DTO.user.UserRequest;
import com.did.MyShop.DTO.user.UserResponse;
import com.did.MyShop.services.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class PersonnelController {
    private final UserService userService;


    @GetMapping("/me")
    public UserResponse me(Principal principal){
        return userService.getUser(principal);
    }

    @GetMapping
    public List<UserResponse> index(){
        return userService.All();
    }


    @PostMapping
    public UserResponse store(
            @Valid @RequestBody UserRequest userRequest
    ){
        return userService.save(userRequest);
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


    @GetMapping("/vente-per-user/{id}")
    private  List<CommandeResponse> venteCaissier(@PathVariable("id") Long personnelId){
        return userService.getCommandePerCaissier(personnelId);

    }

}
