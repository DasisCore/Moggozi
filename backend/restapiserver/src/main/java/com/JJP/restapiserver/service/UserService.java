package com.JJP.restapiserver.service;

import com.JJP.restapiserver.dto.UserDto;
import com.JJP.restapiserver.entity.User;

public interface UserService {

    User registerUser(UserDto userDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void findUserByEmail(String email);

    void changeUserPassword(User user, String  password);

}
