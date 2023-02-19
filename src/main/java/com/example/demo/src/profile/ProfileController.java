package com.example.demo.src.profile;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.profile.model.GetProfileRes;
import com.example.demo.src.profile.model.PostProfileReq;
import com.example.demo.src.profile.model.PostProfileRes;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/profiles")
public class ProfileController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProfileProvider profileProvider;
    @Autowired
    private final ProfileService profileService;
    @Autowired
    private final JwtService jwtService;

    public ProfileController(ProfileProvider profileProvider, ProfileService profileService, JwtService jwtService){
        this.profileProvider = profileProvider;
        this.profileService = profileService;
        this.jwtService = jwtService;
    }

    /**
     * 사용자 프로필 생성 API
     * [POST] /profiles
     * @return BaseResponse<PostProfileRes>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostProfileRes> createProfile(@RequestBody PostProfileReq postProfileReq) {
        try{
            int userIdx = jwtService.getUserIdx();
            PostProfileRes postProfileRes = profileService.createProfile(userIdx, postProfileReq);
            return new BaseResponse<>(postProfileRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 사용자 프로필 상세 조회
     * [GET] /profiles/:profileIdx
     * @return BaseResponse<GetUserRes>
     */
    @ResponseBody
    @GetMapping("/{profileIdx}") // (GET) 127.0.0.1:9000/app/profiles/:profileIdx
    public BaseResponse<GetProfileRes> getUser(@RequestHeader("X-ACCESS-TOKEN") String jwtToken, @PathVariable("profileIdx") int profileIdx) {
        try{
            GetProfileRes getProfileRes = profileProvider.getProfile(profileIdx);
            return new BaseResponse<>(getProfileRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
