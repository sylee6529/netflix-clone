package com.example.demo.src.profile;

import com.example.demo.config.BaseException;
import com.example.demo.src.profile.model.GetProfileRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ProfileProvider {

    private final ProfileDao profileDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProfileProvider(ProfileDao profileDao, JwtService jwtService) {
        this.profileDao = profileDao;
        this.jwtService = jwtService;
    }

    public GetProfileRes getProfile(int profileIdx) throws BaseException {
        try {
            GetProfileRes getProfileRes = profileDao.getProfile(profileIdx);
            return getProfileRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
