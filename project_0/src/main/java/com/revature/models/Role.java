package com.revature.models;

public enum Role {
    /**
     * Role Object
     * For use in further development, to assign specific allowances to users.
     */
    ADMIN("Admin"),
    MANAGER("Manager"),
    PREMIUM_MEMBER("Premium Member"),
    BASIC_MEMBER("Basic Member"),
    LOCKED("Locked");

    private String roleName;

    Role(String Name) {
        this.roleName = Name;
    }

    public static Role getByName(String name) {

        for (Role role : Role.values()) {
            if(role.roleName.equals(name)) {
                return role;
            }
        }

        return LOCKED;

    }

    @Override
    public String toString() {
        return roleName;
    }
}
