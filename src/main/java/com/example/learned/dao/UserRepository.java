package com.example.learned.dao;


import com.example.learned.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity findByEmail(String email);
    UserEntity findByNickname(String nickname);
    @Query(value = "select * from users",nativeQuery = true)
    List<UserEntity> getAllUser();
    @Query(value="SELECT u.id FROM users u LEFT JOIN sub_users s ON u.id = s.user_id WHERE s IS NULL AND AGE(NOW(), u.created_at) > INTERVAL '1 days'",nativeQuery = true)
    List<Long> getExpirdeUsers();
    @Query(value = "select * from users where status='PENDING'",nativeQuery = true)
    List<UserEntity> getVerifyUsers();

}