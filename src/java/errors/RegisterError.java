/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errors;

/**
 *
 * @author Asus
 */
public class RegisterError {

    private String username = "";
    private String password = "";
    private String confirm_pass = "";
    private String email = "";
    private String phone = "";

    public boolean check(String data, int min, int max) {
        return (data.length() >= min) && (data.length() <= max);
    }

    public boolean checkUserName(String user, int min, int max) {
        if (!check(user, min, max)) {
            username = "Username must have length between " + min + " and " + max;
            return false;
        }
        return true;
    }

    public boolean checkPass(String pass, int min, int max) {
        if (!check(pass, min, max)) {
            password = "Password must have length between " + min + " and" + max;
            return false;
        }
        return true;
    }

    public boolean checkConfirmPassword(String confirm_password, String password) {
        if (!confirm_password.equals(password)) {
            confirm_pass = "Password and confirm must be the same";
            return false;
        }
        return true;
    }

    public boolean checkEmail(String mail, int min, int max) {
        if (!check(mail, min, max)) {
            email = "Email must have length between " + min + " and " + max;
            return false;
        }
        return true;
    }

    public boolean checkEmailFormat(String mail) {
        String format = "^[a-zA-Z0-9._]+@gmail\\.com$";
        if (!mail.matches(format) && !mail.matches("^[a-zA-Z0-9._]+@fpt\\.edu\\.vn$")) {
            email = "Email must be in the correct format.";
            return false;
        }
        return true;
    }

    public boolean checkPhone(String phoneNum, int min, int max) {
        if (!check(phoneNum, min, max)) {
            phone = "Phone must have length between " + min + " and " + max;
            return false;
        }
        return true;
    }

    public boolean checkPhoneFormat(String phoneNumber) {
        String format = "^0[0-9]{9,11}$";
        if (!phoneNumber.matches(format)) {
            phone = "Phone must be in the correct format.";
            return false;
        }
        return true;
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

    public String getConfirm_pass() {
        return confirm_pass;
    }

    public void setConfirm_pass(String confirm_pass) {
        this.confirm_pass = confirm_pass;
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

}
