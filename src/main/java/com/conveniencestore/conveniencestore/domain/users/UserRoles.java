package com.conveniencestore.conveniencestore.domain.users;

public enum UserRoles {
    ADMIN("admin"),
    EMPLOYEE("employee");

    private String role;
    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
