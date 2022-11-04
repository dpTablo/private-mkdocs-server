package com.dptablo.pr.mkdocs.server.repository;

import com.dptablo.pr.mkdocs.server.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}
