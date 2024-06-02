//package com.ssafy.trippals.user.service;
//
//import com.ssafy.trippals.common.exception.FileUploadFailException;
//import com.ssafy.trippals.common.exception.UserAlreadyExistsException;
//import com.ssafy.trippals.common.exception.UserAuthException;
//import com.ssafy.trippals.common.file.FileUploadService;
//import com.ssafy.trippals.common.file.UploadedFile;
//import com.ssafy.trippals.user.dao.UserDao;
//import com.ssafy.trippals.user.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//    private final UserDao userDao;
//    private final FileUploadService fileUploadService;
//
//    @Override
//    public Optional<UserDto> login(String email, String password) {
//        return userDao.findUserDataByEmail(email)
//                .filter(u -> u.getPassword().equals(password))
//                .map(u -> { u.setPassword(null); return u; });
//    }
//
//    @Override
//    public boolean signUp(UserDto userDto) {
//        userDao.findUserDataByEmail(userDto.getEmail()).ifPresent(u -> {throw new UserAlreadyExistsException();});
//
//        int modified = userDao.insertUser(userDto);
//
//        if (modified > 0) {
//            return true;
//        } else {
//            throw new UserAlreadyExistsException();
//        }
//    }
//
//    @Override
//    public Optional<UserDto> getUser(String email) {
//        return userDao.findUserDataByEmail(email).stream()
//                .peek(u -> u.setPassword(""))
//                .findFirst();
//    }
//
//    @Override
//    public List<UserDto> searchUser(String keyword) {
//        return userDao.findUserDataByKeyword(keyword).stream()
//                .peek(u -> u.setPassword(""))
//                .toList();
//    }
//
//    @Override
//    public Optional<UserDto> updateUser(UserDto userDto, MultipartFile profileImage) {
//        if (profileImage != null) {
//            UploadedFile uploadedFile = null;
//            try {
//                fileUploadService.deleteImage(userDto.getProfileImage());
//                uploadedFile = fileUploadService.uploadImage(profileImage);
//            } catch (IOException e) {
//                throw new FileUploadFailException(e);
//            }
//
//            userDto.setProfileImage(uploadedFile.getFileUUID());
//        }
//        userDao.updateUser(userDto);
//        return getUser(userDto.getEmail());
//    }
//
//    @Override
//    public boolean verifyPassword(String email, String password) {
//        return userDao.findUserDataByEmail(email)
//                .filter(u -> u.getPassword().equals(password))
//                .isPresent();
//    }
//
//    @Override
//    public boolean updateUserPassword(String email, String currentPassword, String newPassword) {
//        if (!verifyPassword(email, currentPassword)) {
//            throw new UserAuthException();
//        }
//
//        return userDao.updateUserPassword(email, newPassword) == 1;
//    }
//}
