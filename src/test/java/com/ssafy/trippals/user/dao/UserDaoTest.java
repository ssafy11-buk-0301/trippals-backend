package com.ssafy.trippals.user.dao;

import com.ssafy.trippals.user.dto.UserData;
import com.ssafy.trippals.user.dto.UserInsert;
import com.ssafy.trippals.user.dto.UserUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoTest {
    @Autowired UserDao userDao;
    static List<UserInsert> userInsertList = List.of(
        new UserInsert("enjoy1", "enjoy1@ssafy.com", "1234"),
        new UserInsert("enjoy2", "enjoy2@ssafy.com", "1234"),
        new UserInsert("enjoy3", "enjoy3@ssafy.com", "1234"),
        new UserInsert("enjoy4", "enjoy4@ssafy.com", "1234"),
        new UserInsert("trip1", "trip1@ssafy.com", "1234"),
        new UserInsert("trip2", "trip2@ssafy.com", "1234"),
        new UserInsert("trip3", "trippals1@ssafy.com", "1234"),
        new UserInsert("trippals", "trip3@ssafy.com", "1234"),
        new UserInsert("trippals", "trippals2@ssafy.com", "1234"),
        new UserInsert("trippals", "trippals3@ssafy.com", "1234")
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
        Optional<UserData> optionalUserData = userDao.findUserDataByEmail(expectedEmail);

        // then
        assertThat(optionalUserData.isPresent()).isTrue();
        UserData userData = optionalUserData.get();
        assertThat(userData.getEmail()).isEqualTo(expectedEmail);
    }

    @Test
    void findUserDataByKeyword() {
        // given
        String expectedKeyword1 = "enjoy";
        String expectedKeyword2 = "trip";
        Function<String, Predicate<UserData>> verifyKeywordGenerator =
                expectedKeyword ->
                    userData ->
                        userData.getEmail().startsWith(expectedKeyword) ||
                        userData.getName().startsWith(expectedKeyword);

        // when
        List<UserData> actual1 = userDao.findUserDataByKeyword(expectedKeyword1);
        List<UserData> actual2 = userDao.findUserDataByKeyword(expectedKeyword2);

        // then
        assertThat(actual1.isEmpty()).isFalse();
        assertThat(actual2.isEmpty()).isFalse();
        assertThat(actual1).allMatch(verifyKeywordGenerator.apply(expectedKeyword1));
        assertThat(actual2).allMatch(verifyKeywordGenerator.apply(expectedKeyword2));
    }

    @Test
    void updateUser() {
        // given
        UserInsert target = userInsertList.get(0);
        String expectedName = "modified";
        String expectedProfileImage = UUID.randomUUID().toString();

        UserUpdate userUpdate = new UserUpdate(target.getSeq(), expectedName, expectedProfileImage);

        // when
        int modifiedCount = userDao.updateUser(userUpdate);
        Optional<UserData> optionalUserData = userDao.findUserDataByEmail(target.getEmail());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(optionalUserData.isPresent()).isTrue();

        UserData userData = optionalUserData.get();
        assertThat(userData.getName()).isEqualTo(expectedName);
        assertThat(userData.getProfileImage()).isEqualTo(expectedProfileImage);

    }

    @Test
    void updateUserPassword() {
        // given
        UserInsert target = userInsertList.get(0);
        String expectedPassword = "modified";

        // when
        int modifiedCount = userDao.updateUserPassword(target.getSeq(), expectedPassword);
        Optional<UserData> optionalUserData = userDao.findUserDataByEmail(target.getEmail());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(optionalUserData.isPresent()).isTrue();

        UserData userData = optionalUserData.get();
        assertThat(userData.getPassword()).isEqualTo(expectedPassword);
    }

    @Test
    void deleteUser() {
        // given
        UserInsert target = userInsertList.get(0);

        // when
        int modifiedCount = userDao.deleteUser(target.getSeq());
        Optional<UserData> expectedEmpty = userDao.findUserDataByEmail(target.getEmail());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(expectedEmpty.isEmpty()).isTrue();
    }
}