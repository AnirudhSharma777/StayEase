package com.example.demo.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    // Admin Permissions
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    // Manager Permissions
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),

    // Customer Permissions
    CUSTOMER_BOOK_ROOM("customer:book_room"),
    CUSTOMER_VIEW_ROOMS("customer:view_rooms");

    @Getter
    private final String permission;
}
