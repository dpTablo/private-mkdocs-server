package com.dptablo.pr.mkdocs.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class PageViewController {
    private final RestTemplate restTemplate = new RestTemplate();

//    @GetMapping("/**")
    public ModelAndView home(HttpServletRequest request, ModelMap modelMap) {
        modelMap.addAttribute("requestURI", request.getRequestURI());
        modelMap.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/home", modelMap);
    }

    @GetMapping("/home")
    public String home2(HttpServletRequest request, ModelMap modelMap) {
        modelMap.addAttribute("requestURI", "home2!!");
        return "home";
    }

//    @GetMapping("/rt")
    @ResponseBody
    public String rt(HttpServletRequest request, ModelMap modelMap) throws URISyntaxException {
        URI uri = new URI("http", null,
                "localhost", 8000,
                "/docs/getting-started/",
                null, null);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(""), String.class);

        return responseEntity.getBody();
    }
}
