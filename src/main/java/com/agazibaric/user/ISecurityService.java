package com.agazibaric.user;

public interface ISecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
