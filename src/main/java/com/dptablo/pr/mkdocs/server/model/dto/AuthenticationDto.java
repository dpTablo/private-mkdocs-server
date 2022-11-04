package com.dptablo.pr.mkdocs.server.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationDto {
    private String accessToken;
}
