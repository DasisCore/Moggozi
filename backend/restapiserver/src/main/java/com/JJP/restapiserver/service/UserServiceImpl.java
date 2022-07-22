package com.JJP.restapiserver.service;

import com.JJP.restapiserver.dto.Authority;
import com.JJP.restapiserver.dto.UserDto;
import com.JJP.restapiserver.dto.VerificationToken;
import com.JJP.restapiserver.entity.User;
import com.JJP.restapiserver.error.UserAlreadyExistException;
import com.JJP.restapiserver.repository.UserRepository;
import com.JJP.restapiserver.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private SessionRegistry sessionRegistry;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

//    @Autowired
//    private Environment environment;

    @Override
    public User registerUser(UserDto userDto) {
        if(emailExists(userDto.getEmail())) { // 이미 가입한 계정인지 확인
            throw new UserAlreadyExistException(userDto.getEmail() + "은 이미 사용 중인 계정입니다.");
        }

        if(nicknameExists(userDto.getNickname())) { // 이미 사용 중인 닉네임인지 확인
            throw new UserAlreadyExistException(userDto.getNickname() + "은 이미 사용 중인 닉네임입니다.");
        }

        /** 추후 Enum으로 변경 예정 */
        Authority authority = Authority.builder().authority("ROLE_USER").build();

        String email = userDto.getEmail();
        String username = userDto.getUsername();
        String nickname = userDto.getNickname();
        String introduce = userDto.getIntroduce();
        String password = passwordEncoder.encode(userDto.getPassword());
        int isPrivate = userDto.getIs_private(); // 프로필 공개여부
        String img = userDto.getImg();
        int state = userDto.getState(); // 사용자의 권한
        final User user = User.builder().email(email).username(username).nickname(nickname).introduce(introduce).password(password).is_private(isPrivate).img(img).stemail, username, nickname, introduce
                , password, isPrivate, img, state);
        return userRepository.save();
    }

    @Override
    public User getUser(String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if(token != null) {
            return token.getUser();
        } else {
            return null;
        }
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean nicknameExists(final String nickname) {
        return userRepository.findByNickname(nickname) != null;
    }

    /*
    @Override
    public void deleteUser(User user) {
        final VerificationToken verificationToken = tokenRepository.findByUser(user);

        if(verificationToken != null) {
            tokenRepository.delete(verificationToken);
        }

        final PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUser(user);

        if(passwordResetToken != null) {
            passwordResetTokenRepository.delete(passwordResetToken);
        }

        final User user = new User(user.getEmail(), user.getIntroduce(), user.getPassword()
                , user.getIsPrivate(), user.getImg(), 2); // 숨김

    }
     */

    // 이메일로 사용자 찾기 - 아이디 찾기와 연결
    @Override
    public void findUserByEmail(String email) {

    }

    // 비밀번호 변경 - 회원정보 수정 및 비밀번호 찾기와 연결
    @Override
    public void changeUserPassword(User user, String password) {

    }
}
