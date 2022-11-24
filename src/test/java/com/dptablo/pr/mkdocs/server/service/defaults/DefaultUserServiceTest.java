package com.dptablo.pr.mkdocs.server.service.defaults;

import com.dptablo.pr.mkdocs.server.model.dto.UserDto;
import com.dptablo.pr.mkdocs.server.model.entity.User;
import com.dptablo.pr.mkdocs.server.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {
    @InjectMocks
    private DefaultUserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("전체 유저 리스트 정보 조회 테스트")
    @Test
    void getAllUserList() {
        //given
        User user1 = User.builder()
                .userId("user1")
                .phoneNumber("01011112222")
                .build();

        User user2 = User.builder()
                .userId("user2")
                .phoneNumber("01033334444")
                .build();

        given(userRepository.findAll()).willReturn(Arrays.asList(user1, user2));

        //when
        List<UserDto.SimpleUser> allUserList = userService.getAllUserList();

        //then
        assertThat(allUserList.size()).isEqualTo(2);
        assertThat(allUserList.get(0).getUserId()).isEqualTo(user1.getUserId());
        assertThat(allUserList.get(0).getPhoneNumber()).isEqualTo(user1.getPhoneNumber());
        assertThat(allUserList.get(1).getUserId()).isEqualTo(user2.getUserId());
        assertThat(allUserList.get(1).getPhoneNumber()).isEqualTo(user2.getPhoneNumber());
    }

    @Test
    @DisplayName("유저 id, 비밀번호 매칭 정보 조회")
    void getMatchedUser() {
        //given
        final var TEST_USER_ID = "user1";
        final var TEST_PASSWORD = "1234";

        User user1 = User.builder()
                .userId(TEST_USER_ID)
                .password(TEST_PASSWORD)
                .build();
        given(userRepository.findById(user1.getUserId())).willReturn(Optional.of(user1));

        given(passwordEncoder.encode(TEST_PASSWORD)).willReturn(TEST_PASSWORD);
        given(passwordEncoder.matches(TEST_PASSWORD, TEST_PASSWORD)).willReturn(true);

        //when
        var matchedUser = userService.getMatchedUser(TEST_USER_ID, TEST_PASSWORD)
                .orElseThrow(NullPointerException::new);

        //then
        assertThat(matchedUser).isNotNull();
        assertThat(matchedUser.getUserId()).isEqualTo(user1.getUserId());
    }
}