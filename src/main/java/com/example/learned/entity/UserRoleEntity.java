package com.example.learned.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "users_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private RoleEntity role;
}
