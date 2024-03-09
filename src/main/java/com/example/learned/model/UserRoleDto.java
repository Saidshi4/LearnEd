package com.example.learned.model;

import com.example.learned.model.auth.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private Long id;
    private Long userId;
    private RoleDto role;
}
