package com.example.demo.src.profile;

import com.example.demo.config.BaseException;
import com.example.demo.src.profile.model.GetProfileRes;
import com.example.demo.src.profile.model.PostProfileReq;
import com.example.demo.src.profile.model.PostProfileRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.src.user.model.PutUserReq;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_USERNAME;

@Service
public class ProfileService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProfileDao profileDao;
    private final ProfileProvider profileProvider;
    private final JwtService jwtService;


    @Autowired
    public ProfileService(ProfileDao profileDao, ProfileProvider profileProvider, JwtService jwtService) {
        this.profileDao = profileDao;
        this.profileProvider = profileProvider;
        this.jwtService = jwtService;

    }

    public PostProfileRes createProfile(int userIdx, PostProfileReq postProfileReq) throws BaseException {
        try{
            int profileIdx = profileDao.createProfile(userIdx, postProfileReq);
            PostProfileRes postProfileRes = new PostProfileRes(profileIdx);
            return postProfileRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyProfile(int profileId, GetProfileRes getProfileRes) throws BaseException {
        try{
            int result = profileDao.modifyProfile(profileId, getProfileRes);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }

        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
