package com.dptablo.pr.mkdocs.server.repository;

import com.dptablo.pr.mkdocs.server.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomRepository {
    List<User> getUserListBySearchingName(String name);
}
