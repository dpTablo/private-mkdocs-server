package com.dptablo.pr.mkdocs.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public void rootPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }
}
