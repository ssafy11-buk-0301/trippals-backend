package com.ssafy.trippals.user.dao;

import com.ssafy.trippals.user.dto.UserData;
import com.ssafy.trippals.user.dto.UserInsert;
import com.ssafy.trippals.user.dto.UserUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {
    Optional<UserData> findUserDataByEmail(String email);
    List<UserData> findUserDataByKeyword(String keyword);
    int insertUser(UserInsert userInsert);
    int updateUser(UserUpdate userUpdate);
    int updateUserPassword(@Param("email") String email, @Param("password") String password);
    int deleteUser(Integer seq);
}
