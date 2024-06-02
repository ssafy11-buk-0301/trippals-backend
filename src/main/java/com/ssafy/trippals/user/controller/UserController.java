//package com.ssafy.trippals.user.controller;
//
//import com.ssafy.trippals.common.SessionConst;
//import com.ssafy.trippals.board.dto.BoardParamDto;
//import com.ssafy.trippals.board.dto.BoardResultDto;
//import com.ssafy.trippals.board.service.BookmarkService;
//import com.ssafy.trippals.common.exception.UserAuthException;
//import com.ssafy.trippals.common.exception.UserNotFoundException;
//import com.ssafy.trippals.user.dto.UserDto;
//import com.ssafy.trippals.user.dto.UserPasswordUpdateForm;
//import com.ssafy.trippals.user.dto.UserUpdateForm;
//import com.ssafy.trippals.user.service.UserService;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users")
//public class UserController {
//    private final UserService userService;
//    private final BookmarkService bookmarkService;
//    @GetMapping
//    public ResponseEntity<List<UserDto>> searchUser(@RequestParam("keyword") String keyword) {
//        return ResponseEntity.ok(userService.searchUser(keyword));
//    }
//
//    @GetMapping("/{userSeq}")
//    public ResponseEntity<UserDto> getUser(
//            @SessionAttribute(value = SessionConst.USER, required = false) UserDto userDto
//    ) {
//        if (userDto == null) {
//            throw new UserAuthException();
//        }
//
//        return userService.getUser(userDto.getEmail())
//                .map(ResponseEntity::ok)
//                .orElseThrow(UserNotFoundException::new);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserDto> updateUser(
//            @SessionAttribute(value = SessionConst.USER, required = false) UserDto userDto,
//            @ModelAttribute UserUpdateForm userUpdateForm
//    ) {
//        if (userDto == null) {
//            throw new UserAuthException();
//        }
//        userDto.setName(userUpdateForm.getName());
//        Optional<UserDto> optionalUserDto =
//                userService.updateUser(userDto, userUpdateForm.getProfileImage());
//
//        return optionalUserDto
//                .map(ResponseEntity::ok)
//                .orElseThrow(UserNotFoundException::new);
//    }
//
//    @PutMapping("/password")
//    @ResponseStatus(HttpStatus.OK)
//    public void changePassword(
//            @SessionAttribute(value = SessionConst.USER, required = false) UserDto userDto,
//            @RequestBody UserPasswordUpdateForm userPasswordUpdateForm
//    ) {
//        if (userDto == null) {
//            throw new UserAuthException();
//        }
//
//        userService.updateUserPassword(userDto.getEmail(),
//                userPasswordUpdateForm.getCurrentPassword(),
//                userPasswordUpdateForm.getNewPassword());
//    }
//
//    @GetMapping(value="/bookmarks")
//    public ResponseEntity<BoardResultDto> boardListByUser(BoardParamDto boardParamDto,HttpSession session){
//        System.out.println(boardParamDto);
//        int userSeq=((UserDto) session.getAttribute(SessionConst.USER)).getSeq();
//        boardParamDto.setUserSeq(userSeq);
//
//        BoardResultDto boardResultDto=bookmarkService.bookmarkList(boardParamDto);
//        return ResponseEntity.ok(boardResultDto);
//    }
//}
