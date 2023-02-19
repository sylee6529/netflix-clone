package com.example.demo.src.content.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCurrentWatchRes {
    private int seasonIdx;
    private int episodeIdx;
    private String episodeTitle;
    private String remainTime;
    private int watchingRate;
}
