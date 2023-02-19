package com.example.demo.src.profile;

import com.example.demo.config.BaseException;
import com.example.demo.src.profile.model.PostProfileReq;
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

    public int createProfile(int userIdx, PostProfileReq postProfileReq) throws BaseException {
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
}
