/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.util.stream.Stream;

/**
 *
 * @author Asus
 */
public enum Role {

    ADMIN("R1"),
    CUSTOMER("R2"),;

    private final String id;

    private Role(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Role getRole(String value) {
        return Stream.of(Role.values())
                .filter(role -> role.getId().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}

class runCheckRole {
    
    public static void main(String[] args) {
        Role role = Role.CUSTOMER;
        System.out.println(role.equals(Role.CUSTOMER));
    }
    
}
