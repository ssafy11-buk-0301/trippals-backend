package com.ssafy.trippals.user.dao;

import com.ssafy.trippals.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {
    Optional<UserDto> findUserDataByEmail(String email);
    List<UserDto> findUserDataByKeyword(String keyword);
    int insertUser(UserDto userDto);
    int updateUser(UserDto userDto);
    int updateUserPassword(@Param("email") String email, @Param("password") String password);
    int deleteUser(Integer seq);
}
