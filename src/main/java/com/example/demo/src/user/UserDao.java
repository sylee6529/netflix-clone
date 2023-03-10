package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    public List<GetUserRes> getUsers(){
//        String getUsersQuery = "select * from UserInfo";
//        return this.jdbcTemplate.query(getUsersQuery,
//                (rs,rowNum) -> new GetUserRes(
//                        rs.getInt("userIdx"),
//                        rs.getString("userName"),
//                        rs.getString("ID"),
//                        rs.getString("Email"),
//                        rs.getString("password"))
//                );
//    }
//
//    public List<GetUserRes> getUsersByEmail(String email){
//        String getUsersByEmailQuery = "select * from UserInfo where email =?";
//        String getUsersByEmailParams = email;
//        return this.jdbcTemplate.query(getUsersByEmailQuery,
//                (rs, rowNum) -> new GetUserRes(
//                        rs.getInt("userIdx"),
//                        rs.getString("userName"),
//                        rs.getString("ID"),
//                        rs.getString("Email"),
//                        rs.getString("password")),
//                getUsersByEmailParams);
//    }

    public GetUserRes getUser(int userIdx) {
        String getUserQuery = "select userEmail, phoneNumber, cardNumber, membership, startDate, userStatus from User where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getString("userEmail"),
                        rs.getString("phoneNumber"),
                        rs.getString("cardNumber"),
                        rs.getString("membership"),
                        rs.getDate("startDate"),
                        rs.getString("userStatus")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (userEmail, userPasswd, userName, birth, " +
                "cardExpirationDate, membership, cardNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] createUserParams = new Object[]{
                postUserReq.getUserEmail(), postUserReq.getUserPasswd(),
                postUserReq.getUserName(), postUserReq.getBirth(),
                postUserReq.getCardExpirationDate(), postUserReq.getMembership(),
                postUserReq.getCardNumber()
        };

        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public int checkEmail(String email) {
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUser(int userIdx, PutUserReq putUserReq){
        String modifyUserNameQuery = "update User set userEmail = ?, userPasswd = ?, phoneNumber = ? where userIdx = ?";
        Object[] modifyUserNameParams = new Object[]{
                putUserReq.getUserEmail(), putUserReq.getUserPasswd(),
                putUserReq.getPhoneNumber(), userIdx
        };

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq) {
        String getPwdQuery = "select userIdx, userEmail, userName, userPasswd, phoneNumber from User where userEmail = ?";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("userEmail"),
                        rs.getString("userName"),
                        rs.getString("userPasswd"),
                        rs.getString("phoneNumber")
                ),
                getPwdParams
                );

    }


}
