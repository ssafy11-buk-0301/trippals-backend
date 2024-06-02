//package com.ssafy.trippals.route.service;
//
//import com.ssafy.trippals.common.exception.UserAlreadyExistsException;
//import com.ssafy.trippals.common.exception.UserAuthException;
//import com.ssafy.trippals.common.exception.UserNotFoundException;
//import com.ssafy.trippals.common.page.dto.PageParams;
//import com.ssafy.trippals.common.page.dto.PageResponse;
//import com.ssafy.trippals.event.EventService;
//import com.ssafy.trippals.route.dao.RouteDao;
//import com.ssafy.trippals.route.dao.RouteEditorDao;
//import com.ssafy.trippals.route.dto.RouteDto;
//import com.ssafy.trippals.route.dto.RouteEditorDto;
//import com.ssafy.trippals.route.dto.RouteEditorRequestDto;
//import com.ssafy.trippals.user.dao.UserDao;
//import com.ssafy.trippals.user.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class RouteEditorServiceImpl implements RouteEditorService {
//    private final RouteDao routeDao;
//    private final RouteEditorDao routeEditorDao;
//    private final UserDao userDao;
//    private final EventService eventService;
//
//    @Override
//    public List<UserDto> findAllEditors(int userSeq, int routeSeq) {
//        if (!canEdit(routeSeq, userSeq)) throw new UserAuthException();
//
//        return routeEditorDao.findEditorByRouteSeq(routeSeq);
//    }
//
//    @Override
//    public PageResponse<RouteDto> findAllEditableRoutes(int userSeq, PageParams pageParams) {
//        List<RouteDto> contents = routeEditorDao.findRouteByEditorSeq(userSeq, pageParams.getOffset(), pageParams.getLimit());
//        int contentsSize = routeEditorDao.countRouteByEditorSeq(userSeq);
//        return new PageResponse<>(contents, pageParams, contentsSize);
//    }
//
//    @Override
//    public boolean addEditor(int routeSeq, int owner, int editor) {
//        if (!isOwner(routeSeq, owner)) throw new UserAuthException();
//
//        int modified = routeEditorDao.insertRouteEditor(new RouteEditorDto(routeSeq, editor));
//
//        return modified == 1;
//    }
//
//    @Override
//    public boolean removeEditor(int routeSeq, int owner, int editor) {
//        if (!isOwner(routeSeq, owner)) throw new UserAuthException();
//
//        int modified = routeEditorDao.deleteRouteEditor(routeSeq, editor);
//
//        return modified == 1;
//    }
//
//    @Override
//    public List<RouteEditorRequestDto> findAllRequests(int userSeq) {
//        return routeEditorDao.findRequestByUserSeq(userSeq);
//    }
//
//    @Override
//    public boolean addRequest(int routeSeq, int owner, String editorEmail) {
//        if (!isOwner(routeSeq, owner)) throw new UserAuthException();
//
//        UserDto editor = userDao.findUserDataByEmail(editorEmail).orElseThrow(UserNotFoundException::new);
//        eventService.sendRequestAddEvent(editor.getSeq());
//
//        if (canEdit(routeSeq, editor.getSeq())) throw new UserAlreadyExistsException();
//
//        return 1 == routeEditorDao.insertRouteEditorRequest(new RouteEditorRequestDto(routeSeq, null, editor.getSeq()));
//    }
//
//    @Override
//    public boolean confirmRequest(int routeSeq, int userSeq) {
//        if (canEdit(routeSeq, userSeq)) throw new UserAlreadyExistsException();
//
//        List<RouteEditorRequestDto> requestList = routeEditorDao.findRequestByUserSeq(userSeq);
//
//        requestList.stream()
//                .filter(r -> r.getRouteSeq() == routeSeq)
//                .limit(1)
//                .peek(r -> routeEditorDao.deleteRequestByUserSeqAndRouteSeq(userSeq, routeSeq))
//                .map(r -> new RouteEditorDto(routeSeq, userSeq))
//                .peek(routeEditorDao::insertRouteEditor)
//                .findAny()
//                .orElseThrow(UserAuthException::new);
//
//        return true;
//    }
//
//    @Override
//    public boolean rejectRequest(int routeSeq, int userSeq) {
//        if (canEdit(routeSeq, userSeq)) throw new UserAlreadyExistsException();
//
//        List<RouteEditorRequestDto> requestList = routeEditorDao.findRequestByUserSeq(userSeq);
//
//        requestList.stream()
//                .filter(r -> r.getRouteSeq() == routeSeq)
//                .limit(1)
//                .peek(r -> routeEditorDao.deleteRequestByUserSeqAndRouteSeq(userSeq, routeSeq))
//                .findAny()
//                .orElseThrow(UserAuthException::new);
//
//        return true;
//    }
//
//    @Override
//    public boolean canEdit(int routeSeq, int userSeq) {
//        return isOwner(routeSeq, userSeq) || isEditor(routeSeq, userSeq);
//    }
//
//    @Override
//    public boolean isEditor(int routeSeq, int userSeq) {
//        return routeEditorDao.findEditorByRouteSeq(routeSeq).stream()
//                .anyMatch(u -> u.getSeq() == userSeq);
//    }
//
//    @Override
//    public boolean isOwner(int routeSeq, int userSeq) {
//        return routeDao.findRouteDtoBySeq(routeSeq)
//                .filter(r -> r.getOwner() == userSeq)
//                .isPresent();
//    }
//}
