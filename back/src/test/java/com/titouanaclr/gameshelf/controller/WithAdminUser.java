package com.titouanaclr.gameshelf.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(authorithies = "ROLE_ADMIN")
public @interface WithAdminUser {
}
