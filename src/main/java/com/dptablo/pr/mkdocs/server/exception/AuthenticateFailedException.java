package com.dptablo.pr.mkdocs.server.exception;

public class AuthenticateFailedException extends ApplicationException {
    public AuthenticateFailedException() {
        super(ApplicationErrorCode.AUTHENTICATION_ID_OR_PASSWORD_MISMATCH,
                ApplicationErrorCode.AUTHENTICATION_ID_OR_PASSWORD_MISMATCH.getDescription());
    }
}
