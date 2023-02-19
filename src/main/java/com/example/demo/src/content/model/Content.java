package com.example.demo.src.content.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Content {
    private GetContentInfoRes contentInfo;
    private List<String> creators;
    private List<String> casts;
    private List<String> genres;
    private List<String> keywords;
    private List<GetEpisodeInfoRes> episodeInfo;
    private GetCurrentWatchRes currentWatch;
}
