package com.example.demo.src.content;

import com.example.demo.config.BaseException;
import com.example.demo.src.content.model.*;
import com.example.demo.utils.JwtService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ContentProvider {

    private final ContentDao contentDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ContentProvider(ContentDao contentDao, JwtService jwtService) {
        this.contentDao = contentDao;
        this.jwtService = jwtService;
    }

    public GetContentInfoRes getContentInfo(int contentId, int profileId) throws BaseException {
        try {
            GetContentInfoRes getContentRes = contentDao.getContentInfo(contentId, profileId);
            return getContentRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<String> getContentCreators(int contentId) throws BaseException {
        try {
            List<String> getContentCreatorsRes = contentDao.getContentCreators(contentId);
            return getContentCreatorsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<String> getContentGenres(int contentId) throws BaseException {
        try {
            List<String> getContentGenresRes = contentDao.getContentGenres(contentId);
            return getContentGenresRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<String> getContentCasts(int contentId) throws BaseException {
        try {
            List<String> getContentCastsRes = contentDao.getContentCasts(contentId);
            return getContentCastsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<String> getContentKeywords(int contentId) throws BaseException {
        try{
            List<String> getContentKeywordsRes = contentDao.getContentKeywords(contentId);
            return getContentKeywordsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetEpisodeInfoRes> getEpisodeInfo(int contentId, int seasonIdx) throws BaseException {
        try {
            List<GetEpisodeInfoRes> getEpisodeInfoRes = contentDao.getEpisodeInfo(contentId, seasonIdx);
            return getEpisodeInfoRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetCurrentWatchRes getCurrentWatch(int contentId, int profileId) throws BaseException {
        try {
            GetCurrentWatchRes getCurrentWatchRes = contentDao.getCurrentWatch(contentId, profileId);
            return getCurrentWatchRes;
        }  catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Content getContent(int contentId, int profileId, int seasonIdx) throws BaseException {
        try {
            Content content = new Content(getContentInfo(contentId, profileId), getContentCreators(contentId),
                    getContentCasts(contentId), getContentGenres(contentId),  getContentKeywords(contentId),
                    getEpisodeInfo(contentId, seasonIdx), getCurrentWatch(contentId, profileId));
            return content;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
