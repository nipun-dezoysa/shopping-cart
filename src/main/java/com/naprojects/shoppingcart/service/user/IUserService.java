package com.naprojects.shoppingcart.service.user;

import com.naprojects.shoppingcart.model.User;
import com.naprojects.shoppingcart.request.CreateUserRequest;
import com.naprojects.shoppingcart.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    //UserDto convertUserToDto(User user);
}
