package com.dptablo.pr.mkdocs.server.service.defaults;

import com.dptablo.pr.mkdocs.server.model.dto.UserDto;
import com.dptablo.pr.mkdocs.server.repository.UserRepository;
import com.dptablo.pr.mkdocs.server.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto.SimpleUser> getAllUserList() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto.SimpleUser(user))
                .toList();
    }
}
