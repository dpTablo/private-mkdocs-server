package com.dptablo.pr.mkdocs.server.service;

import com.dptablo.pr.mkdocs.server.model.dto.UserDto;
import com.dptablo.pr.mkdocs.server.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto.SimpleUser> getAllUserList();
    Optional<User> getUser(String userId);
    Optional<User> getMatchedUser(String userId, String password);
}
