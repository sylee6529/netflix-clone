package com.example.demo.src.content;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.content.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/contents")
public class ContentController {

    @Autowired
    private final ContentProvider contentProvider;
    @Autowired
    private final ContentService contentService;
    @Autowired
    private final JwtService jwtService;

    public ContentController(ContentProvider contentProvider, ContentService contentService, JwtService jwtService) {
        this.contentProvider = contentProvider;
        this.contentService = contentService;
        this.jwtService = jwtService;
    }

    /**
     * 컨텐츠 조회
     * [GET] /contents/:contentId
     * @return BaseResponse<GetContentRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{contentId}")  // (GET) 127.0.0.1:9000/app/contents/:contentId?profileId=&seasonIdx=
    public BaseResponse<Content> getContent(@PathVariable("contentId") int contentId, @RequestParam int profileId, @RequestParam(defaultValue = "1") int seasonIdx) {
        // Get Content
        try {
            Content res = contentProvider.getContent(contentId, profileId, seasonIdx);
            return new BaseResponse<>(res);
        } catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
