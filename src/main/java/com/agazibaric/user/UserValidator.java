package com.agazibaric.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty", "This field cannot be empty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username", "Username must have between 3 and 32 characters(not included the bounds)");
        }

        if (userRepo.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username", "There is already user with such username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty", "This field cannot be empty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password", "password must have between 4 and 32 characters(not included the bounds)");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty", "This field cannot be empty");
        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "Same.userForm.passwordConfirm", "Password confimation is not the same as password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty", "This field cannot be empty");
        if(!user.getEmail().contains("@") || !user.getEmail().contains(".") || user.getEmail().length() < 3) {
            errors.rejectValue("email", "Size.userForm.email", "Email is not valid");
        }

    }
}
