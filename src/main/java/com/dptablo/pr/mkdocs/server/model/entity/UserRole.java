package com.dptablo.pr.mkdocs.server.model.entity;

import com.dptablo.pr.mkdocs.server.model.enumtype.Role;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode
public class UserRole implements Serializable {
    @Id
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String description;
}
