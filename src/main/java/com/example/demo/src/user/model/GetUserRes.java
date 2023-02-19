package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private String userEmail;
    private String phoneNumber;
    private String cardNumber;
    private String membership;
    private Date startDate;
    private String userStatus;
}
