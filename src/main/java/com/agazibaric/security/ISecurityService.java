package com.agazibaric.security;

public interface ISecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
