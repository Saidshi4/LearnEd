package com.example.learned.entity;

import com.example.learned.entity.enums.EUserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private LocalDate birthDate;
    private String serialNumber;
    private String finCode;
    private String cityOrDistrict;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private EUserStatus status;
    private String password;
    private Boolean educationalInstitution;
    private Boolean masterDegree;
    private Boolean educationStatus;
    private String imageUrl;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
//    @OneToMany(mappedBy = "user")
//    private List<SubUserEntity> userEntities;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
//    private List<UserFileEntity> userFileEntity;
    @OneToMany(fetch=FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserRoleEntity> userRoles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().getName()))
                .collect(Collectors.toList());
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
