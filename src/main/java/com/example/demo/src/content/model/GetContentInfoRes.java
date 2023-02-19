package com.example.demo.src.content.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetContentInfoRes {
    private String contentTitle;
    private String previewImgURL;
    private String previewVideoURL;
    private int releasedYear;
    private String maturityRating;
    private int seasonCount;
    private String contentIntroduction;
    private String contentPosterURL;
    private String contentCatchPhrase;
    private String isReleased;
    private String isAvailToDownload;
    private String isInMyList;
    private int myMatchScore;
    private int myRate;
}
