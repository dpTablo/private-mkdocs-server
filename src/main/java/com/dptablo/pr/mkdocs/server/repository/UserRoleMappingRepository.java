package com.dptablo.pr.mkdocs.server.repository;

import com.dptablo.pr.mkdocs.server.model.entity.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long> {
}
