package com.example.demo.src.content.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetEpisodeInfoRes {
    private int partNo;
    private String episodeTitle;
    private String videoURL;
    private String videoLength;
    private String episodeIntroduction;
    private String watchingRate;
    private String downloadStatus;
}
