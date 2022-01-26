package com.ssafy.BackEnd.service;

public interface EmailService {
    void sendMail(String to, String sub, String text);
}
