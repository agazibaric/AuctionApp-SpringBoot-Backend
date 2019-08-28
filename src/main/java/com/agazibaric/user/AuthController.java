package com.agazibaric.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<BindingResult> registration(@RequestBody User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult);
        }

        String plainPw = userForm.getPassword();
        String hashedPw = passwordEncoder.encode(plainPw);
        userForm.setPassword(hashedPw);
        userRepo.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User currentUserName(Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        System.out.println(principal.getName());
        return user;
    }

}
