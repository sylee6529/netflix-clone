package com.example.demo.src.profile;

import com.example.demo.config.BaseException;
import com.example.demo.src.profile.model.GetProfileRes;
import com.example.demo.src.profile.model.PostProfileReq;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ProfileDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createProfile(int userIdx, PostProfileReq postProfileReq) {
        String maturityRating = "";
        if(postProfileReq.isKidProfile()) {
            maturityRating = "KIDS";
        }
        else {
            maturityRating = "ALL";
        }

        String createProfileQuery = "insert into UserProfile (userIdx, imageId, profileName, maturityRating) values (?, ?, ?, ?)";
        Object[] createProfileParams = new Object[] {
                userIdx, postProfileReq.getImageId(), postProfileReq.getProfileName(), maturityRating
        };

        this.jdbcTemplate.update(createProfileQuery, createProfileParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public GetProfileRes getProfile(int profileId) {
        String getProfileQuery = "select profileName, imageId, maturityRating, displayLanguage, audioSubtitleLanguage, " +
                "isEpisodeAutoplay, isPreviewAutoplay from UserProfile where profileId = ?";
        int getProfileParams = profileId;
        return this.jdbcTemplate.queryForObject(getProfileQuery,
                (rs, rowNum) -> new GetProfileRes(
                        rs.getString("profileName"),
                        rs.getInt("imageId"),
                        rs.getString("maturityRating"),
                        rs.getString("displayLanguage"),
                        rs.getString("audioSubtitleLanguage"),
                        rs.getString("isEpisodeAutoplay"),
                        rs.getString("isPreviewAutoplay")),
                getProfileParams);
    }
}
