package com.did.MyShop.entities.User;

import com.did.MyShop.enums.GenreUserEnum;
import com.did.MyShop.enums.StatusUserEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private GenreUserEnum genre;
    private String email;
    private String dateNaissance;
    private String phoneNumber;
    private String matricule;
    private String password;

    @Enumerated(EnumType.STRING)
    private StatusUserEnum status;

    @ManyToMany(mappedBy = "users",cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ResetPasswordJeton> resetPasswords;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getLibelle()))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return matricule;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(StatusUserEnum.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(StatusUserEnum.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(StatusUserEnum.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(StatusUserEnum.ACTIVE);
    }
    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);  // Assurer la bidirectionnalité
    }

    public String fullname(){
        return getFirstName() + " " + getLastName();
    }

}
