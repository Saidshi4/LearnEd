package com.example.learned.model.auth;

import com.example.learned.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleDto {
    private Long id;
    private ERole name;

}
