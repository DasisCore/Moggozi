package com.JJP.restapiserver.repository;

import com.JJP.restapiserver.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

// JPA를 직접 사용해서 쿼리 호출하는 Repository

@Repository
public class UserRepositoryJPA {

    @PersistenceContext
    private EntityManager em;

    // 회원 정보 저장 (가입)
    public User save(User user) {
        em.persist(user);
        return user;
    }

    // 회원 삭제
    public void delete(User user) {
        em.remove(user);
    }

    // 총 회원이 몇 명인지 카운트 반환
    public long count() {
        return em.createQuery("select count(u) from User u", Long.class).getSingleResult();
    }

    // user_id로 유저 찾기
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user); // null이라면 회원은 존재하지 않는 것
    }

    // 등록된 유저 모두 반환
    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
    }

    /** 동적 쿼리로 변경 예정 */
    // 닉네임(키워드)으로 유저 찾기 - 닉네임이 다 입력되지 않았을 경우
    public List<User> findByNicknameContaining(String nickname) {
        return em.createQuery("select u from User u where u.nickname like :nickname")
                .setParameter("nickname", nickname).getResultList();
    }

    // 닉네임과 일치하는 유저 찾기
    public User findByNickname(String nickname) {
        return em.createQuery("select u from User u where u.nickname = :nickname", User.class)
                .setParameter("nickname", nickname).getSingleResult();
    }

    // username(이메일)로 유저 찾기
    public User findByUserName(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }


}
