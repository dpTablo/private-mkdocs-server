package com.dptablo.pr.mkdocs.server.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.dptablo.pr.mkdocs.server.model.entity"})
@EnableJpaRepositories(basePackages = {"com.dptablo.pr.mkdocs.server.repository"})
public class JpaConfiguration {
}
