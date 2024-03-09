package com.example.learned.dao;

import com.example.learned.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRolesRepository extends JpaRepository<UserRoleEntity,Long> {

}
