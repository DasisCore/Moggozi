package com.JJP.restapiserver.repository;

import com.JJP.restapiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByNickname(String nickname);

}
