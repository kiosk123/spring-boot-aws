package com.hjt.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * 이미 생성된 사용자인지 이메일로 판단
     * @param <User> 
     * @param email
     */ 
    Optional<User> findByEmail(String email); 
}
