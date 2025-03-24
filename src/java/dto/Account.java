/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import mylib.Role;

/**
 *
 * @author Asus
 */
public class Account {

    private int UserId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Role role;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String city;

    public Account() {
    }



    public Account(int UserId, String username, String password, String email, String phone, Role role, String name, String street, String ward, String district, String city) {
        this.UserId = UserId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.name = name;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Account{" + "UserId=" + UserId + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", role=" + role + ", name=" + name + ", street=" + street + ", ward=" + ward + ", district=" + district + ", city=" + city + '}';
    }
    
    

    
    
    
        
}
