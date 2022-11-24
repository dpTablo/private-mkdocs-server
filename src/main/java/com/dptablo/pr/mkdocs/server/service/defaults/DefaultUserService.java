package com.dptablo.pr.mkdocs.server.service.defaults;

import com.dptablo.pr.mkdocs.server.model.dto.UserDto;
import com.dptablo.pr.mkdocs.server.model.entity.User;
import com.dptablo.pr.mkdocs.server.repository.UserRepository;
import com.dptablo.pr.mkdocs.server.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto.SimpleUser> getAllUserList() {
        return userRepository.findAll().stream()
                .map(UserDto.SimpleUser::new)
                .toList();
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getMatchedUser(String userId, String password) {
        try {
            var user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
            if(passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(password))) {
                return Optional.of(user);
            } else {
                throw new Exception("not matched user id or password.");
            }
        } catch(Exception e) {
            return Optional.empty();
        }
    }


}
