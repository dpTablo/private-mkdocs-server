package com.dptablo.pr.mkdocs.server.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
    private int code;
    private String message;
    private HttpDto data;
}