package com.dptablo.pr.mkdocs.server.service;

import com.dptablo.pr.mkdocs.server.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto.SimpleUser> getAllUserList();
}
