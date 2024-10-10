package com.titouanaclr.gameshelf.model;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {

    private Integer id;
    private String username;
    private Date createdAt;
    private Set<Role> roles;
}
