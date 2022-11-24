package com.dptablo.pr.mkdocs.server.repository;

import com.dptablo.pr.mkdocs.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserIdAndPassword(String userId, String password);
}
