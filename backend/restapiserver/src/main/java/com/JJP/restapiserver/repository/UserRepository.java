package com.JJP.restapiserver.repository;


import com.JJP.restapiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.nickname = :nickname")
    User findByNickname(@Param("nickname") String nickname);

    @Query(value = "select u from User u where u.username = :username")
    User findByUsername(@Param("username") String nickname);

    // 입력한 닉네임 키워드를 포함하고 있는 경우를 찾아 리스트로 반환
    @Query(value = "select u from User u where u.nickname like :nickname")
    List<User> findByNicknameContaining(@Param("nickname") String nickname);

    @Modifying
    @Query("update User u set u = :user")
    int updateUser(@Param("user") User user);

    // 프로필 사진만 수정할 경우
    @Modifying
    @Query("update User u set u.user_img = :user_img")
    int updateUserImg(@Param("user_img") String user_img);

}
