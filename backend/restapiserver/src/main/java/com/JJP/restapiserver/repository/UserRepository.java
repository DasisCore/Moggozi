package com.JJP.restapiserver.repository;

import com.JJP.restapiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자를 찾은 뒤 return, null일 경우 존재하지 않는 사용자
    Optional<User> findByEmail(String email);

    // 존재하는 이메일인지 확인하여 boolean type으로 return
    boolean existsByEmail(String email);

    // 존재하는 닉네임인지 확인하여 boolean type으로 return
    boolean existsByNickname(String nickname);

}
