package com.titouanaclr.gameshelf.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(userId = 101, authorithies = "ROLE_ADMIN")
public @interface WithMockAdmin {
}
