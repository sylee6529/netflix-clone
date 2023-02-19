package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String userEmail;
    private String userPasswd;
    private String userName;
    private String birth;
    private String cardExpirationDate;
    private String membership;
    private String cardNumber;
}
