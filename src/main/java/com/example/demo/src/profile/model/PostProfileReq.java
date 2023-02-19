package com.example.demo.src.profile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostProfileReq {
    private int imageId;
    private String profileName;
    private boolean isKidProfile;
}
