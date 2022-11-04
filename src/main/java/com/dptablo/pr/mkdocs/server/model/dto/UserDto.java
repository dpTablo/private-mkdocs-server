package com.dptablo.pr.mkdocs.server.model.dto;

import com.dptablo.pr.mkdocs.server.model.HttpDto;
import com.dptablo.pr.mkdocs.server.model.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class UserDto {
    @NoArgsConstructor
    @Data
    public static class SimpleUser implements HttpDto {
        private String userId;
        private String phoneNumber;

        public SimpleUser(User user) {
            this.userId = user.getUserId();
            this.phoneNumber = user.getPhoneNumber();
        }
    }
}
