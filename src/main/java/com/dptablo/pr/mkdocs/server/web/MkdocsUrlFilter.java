package com.dptablo.pr.mkdocs.server.web;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class MkdocsUrlFilter implements Filter {
    enum MkdocsUrlFilteringCase {
        IS_APPEND_SLASH_REDIRECT_CASE,
        IS_FORWARD_INDEX_HTML_CASE,
        IS_THROUGH_CASE
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) request;
        var uri = httpServletRequest.getRequestURI();

        switch (isMkdocsFilteringCase(uri)) {
            case IS_APPEND_SLASH_REDIRECT_CASE -> ((HttpServletResponse) response).sendRedirect(uri + "/");
            case IS_FORWARD_INDEX_HTML_CASE -> {
                var dispatcher = httpServletRequest.getRequestDispatcher(uri + "index.html");
                dispatcher.forward(request, response);
            }
            default -> chain.doFilter(request, response);
        }
    }

    private MkdocsUrlFilteringCase isMkdocsFilteringCase(String uri) {
        var matchedMkdocsPattern = matchedMkdocsPattern(uri);
        var isNothingExtension = isNothingExtension(uri);
        var isSlashLastCharacter = isSlashLastCharacter(uri);

        if(matchedMkdocsPattern && isNothingExtension && !isSlashLastCharacter)
            return MkdocsUrlFilteringCase.IS_APPEND_SLASH_REDIRECT_CASE;

        if(matchedMkdocsPattern && isNothingExtension && isSlashLastCharacter)
            return MkdocsUrlFilteringCase.IS_FORWARD_INDEX_HTML_CASE;

        return MkdocsUrlFilteringCase.IS_THROUGH_CASE;
    }

    private boolean matchedMkdocsPattern(String uri) {
        return uri.matches("^\\/docs.*");
    }

    private boolean isNothingExtension(String uri) {
        return uri.matches("([^.]*$)");
    }

    private boolean isSlashLastCharacter(String uri) {
        var uriLength = uri.length();
        return uriLength >= 2 && uri.substring(uriLength - 1, uriLength).equals("/");
    }
}
