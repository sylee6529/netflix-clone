package com.example.demo.src.profile.model;

import com.sun.imageio.plugins.common.SingleTileRenderedImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetProfileRes {
    private String profileName;
    private int imageId;
    private String maturityRating;
    private String displayLanguage;
    private String audioAndsubtitleLanguage;
    private String isEpisodeAutoplay;
    private String isPreviewAutoplay;
}
