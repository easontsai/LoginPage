package com.advanced.demo.web;

import com.advanced.demo.domain.User;
import com.advanced.demo.domain.UserRepository;
import com.advanced.demo.form.UserForm;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userForm",new UserForm());
        return "register";
    }
    @GetMapping("/login")
    public String LoginPage(){
        return "login";
    }
    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult result,Model model) {
        /*User user = converFor(userForm);*/
        /*User user = new UserFormConvert().convert(userForm);*/
        /*BeanUtils.copyProperties(userForm,user);*//*userform cpoy to user*/
        boolean boo =true;
        if(!userForm.confirmPassword()){
            result.rejectValue("confirmPassword","confirmError","兩次密碼不一樣");
            /*rejectValue(String field, String errorCode, String defaultMessage)*/
            boo = false;
        }
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors) {
                System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode());
            }
            boo = false;
        }

        if(!boo){
            return "register";
        }
        User user = userForm.ConverToUser();
        userRepository.save(user);
        return "redirect:/login";
    }
}
