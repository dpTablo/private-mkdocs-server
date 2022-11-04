package com.dptablo.pr.mkdocs.server.model.dto;

import com.dptablo.pr.mkdocs.server.model.HttpDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginInfoDto implements HttpDto {
    private String userId;
    private String password;
}
