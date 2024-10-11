package com.titouanaclr.gameshelf.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Schema(description = "Represents a user role in the system.")
@Data
@Entity
@Table(name = "role")
public class Role {

    @Schema(description = "The unique identifier of the role.", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "The name of the role, represented as an enumerated type.", example = "ROLE_USER")
    @Enumerated(EnumType.STRING)
    @Column
    private ERole name;
}
