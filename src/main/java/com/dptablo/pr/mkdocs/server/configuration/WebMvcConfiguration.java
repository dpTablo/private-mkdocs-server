package com.dptablo.pr.mkdocs.server.configuration;

import com.dptablo.pr.mkdocs.server.web.MkdocsUrlFilter;
import com.dptablo.pr.mkdocs.server.web.MkdocsUrlHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final MkdocsUrlHandler mkdocsUrlHandler;
    private final MkdocsUrlFilter mkdocsUrlFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mkdocsUrlHandler);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var docsPath = Paths.get("/Users/tablo/Downloads/ngenebio-docs/docs")
                .toAbsolutePath().normalize();

        registry.addResourceHandler("/docs/**")
                .addResourceLocations(docsPath.toUri().toString())
                .setCachePeriod(20);
    }

    @Bean
    public FilterRegistrationBean<MkdocsUrlFilter> loggingFilter(){
        FilterRegistrationBean<MkdocsUrlFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(mkdocsUrlFilter);
        registrationBean.addUrlPatterns("/docs/*");
        return registrationBean;
    }
}
