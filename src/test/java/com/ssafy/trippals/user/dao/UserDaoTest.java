package com.ssafy.trippals.user.dao;

import com.ssafy.trippals.user.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoTest {
    @Autowired UserDao userDao;
    static List<UserDto> userInsertList = List.of(
        new UserDto("enjoy1", "1234", "enjoy1@ssafy.com", "test", now()),
        new UserDto("enjoy2", "1234", "enjoy2@ssafy.com", "test", now()),
        new UserDto("enjoy3", "1234", "enjoy3@ssafy.com", "test", now()),
        new UserDto("enjoy4", "1234", "enjoy4@ssafy.com", "test", now()),
        new UserDto("trip1", "1234", "trip1@ssafy.com", "test", now()),
        new UserDto("trip2", "1234", "trip2@ssafy.com", "test", now()),
        new UserDto("trip3", "1234", "trippals1@ssafy.com", "test", now()),
        new UserDto("trippals", "1234", "trip3@ssafy.com", "test", now()),
        new UserDto("trippals", "1234", "trippals2@ssafy.com", "test", now()),
        new UserDto("trippals", "1234", "trippals3@ssafy.com", "test", now())
    );

    @BeforeEach
    void setUp() {
        userInsertList.forEach(userDao::insertUser);
    }

    @Test
    void findUserDataByEmail() {
        // given
        String expectedEmail = "trippals2@ssafy.com";

        // when
        Optional<UserDto> optionalUserData = userDao.findUserDataByEmail(expectedEmail);

        // then
        assertThat(optionalUserData.isPresent()).isTrue();
        UserDto userData = optionalUserData.get();
        assertThat(userData.getEmail()).isEqualTo(expectedEmail);
    }

    @Test
    void findUserDataByKeyword() {
        // given
        String expectedKeyword1 = "enjoy";
        String expectedKeyword2 = "trip";
        Function<String, Predicate<UserDto>> verifyKeywordGenerator =
                expectedKeyword ->
                    userData ->
                        userData.getEmail().startsWith(expectedKeyword) ||
                        userData.getName().startsWith(expectedKeyword);

        // when
        List<UserDto> actual1 = userDao.findUserDataByKeyword(expectedKeyword1);
        List<UserDto> actual2 = userDao.findUserDataByKeyword(expectedKeyword2);

        // then
        assertThat(actual1.isEmpty()).isFalse();
        assertThat(actual2.isEmpty()).isFalse();
        assertThat(actual1).allMatch(verifyKeywordGenerator.apply(expectedKeyword1));
        assertThat(actual2).allMatch(verifyKeywordGenerator.apply(expectedKeyword2));
    }

    @Test
    void updateUser() {
        // given
        UserDto target = userInsertList.get(0);
        String expectedName = "modified";
        String expectedProfileImage = UUID.randomUUID().toString();

        UserDto userUpdate = new UserDto(target.getSeq(), expectedName, null, null, expectedProfileImage, null);

        // when
        int modifiedCount = userDao.updateUser(userUpdate);
        Optional<UserDto> optionalUserData = userDao.findUserDataByEmail(target.getEmail());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(optionalUserData.isPresent()).isTrue();

        UserDto userData = optionalUserData.get();
        assertThat(userData.getName()).isEqualTo(expectedName);
        assertThat(userData.getProfileImage()).isEqualTo(expectedProfileImage);

    }

    @Test
    void updateUserPassword() {
        // given
        UserDto target = userInsertList.get(0);
        String expectedPassword = "modified";

        // when
        int modifiedCount = userDao.updateUserPassword(target.getEmail(), expectedPassword);
        Optional<UserDto> optionalUserData = userDao.findUserDataByEmail(target.getEmail());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(optionalUserData.isPresent()).isTrue();

        UserDto userData = optionalUserData.get();
        assertThat(userData.getPassword()).isEqualTo(expectedPassword);
    }

    @Test
    void deleteUser() {
        // given
        UserDto target = userInsertList.get(0);

        // when
        int modifiedCount = userDao.deleteUser(target.getSeq());
        Optional<UserDto> expectedEmpty = userDao.findUserDataByEmail(target.getEmail());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(expectedEmpty.isEmpty()).isTrue();
    }
}