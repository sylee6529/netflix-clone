package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PutUserReq {
    private String userEmail;
    private String userPasswd;
    private String phoneNumber;
}
