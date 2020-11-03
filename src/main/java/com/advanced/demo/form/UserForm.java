package com.advanced.demo.form;

import com.advanced.demo.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;


import javax.validation.constraints.Pattern;

public class UserForm {

    public static final String PHONE_REG = "^09[0-9]{8}$";

    @NotBlank
    private String username;
    @NotBlank
    @Length(min = 6,message = "密碼至少六位")
    private String password;
    @Pattern(regexp = PHONE_REG , message = "請輸入正確手機號")
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String confirmPassword;

    public UserForm() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean confirmPassword(){
        if(this.password.equals(this.confirmPassword)){
            return true;
        }
            return false;
    }
    public User ConverToUser(){
        User user = new UserFormConvert().convert(this);
        return user;
    }


    public class UserFormConvert implements FormConvert<UserForm, User>{

        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            return user;
        }
    }
}
