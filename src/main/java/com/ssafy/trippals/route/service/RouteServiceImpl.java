//package com.ssafy.trippals.route.service;
//
//import com.ssafy.trippals.common.exception.FileUploadFailException;
//import com.ssafy.trippals.common.exception.RouteLimitExceededException;
//import com.ssafy.trippals.common.exception.UserAuthException;
//import com.ssafy.trippals.common.file.FileUploadService;
//import com.ssafy.trippals.common.file.UploadedFile;
//import com.ssafy.trippals.route.dao.RouteDao;
//import com.ssafy.trippals.route.dto.RouteDto;
//import com.ssafy.trippals.route.dto.RouteForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class RouteServiceImpl implements RouteService {
//    static final int MAX_ROUTE_COUNT = 10;
//    private final RouteDao routeDao;
//    private final FileUploadService fileUploadService;
//
//    @Override
//    public void createRoute(int owner, RouteForm routeForm) {
//        if (routeDao.findRouteDtoByOwner(owner).size() >= MAX_ROUTE_COUNT) {
//            throw new RouteLimitExceededException(MAX_ROUTE_COUNT);
//        }
//
//        String fileUUID = null;
//        if (routeForm.getThumbnail() != null) {
//            try {
//                UploadedFile uploadedFile = fileUploadService.uploadImage(routeForm.getThumbnail());
//                fileUUID = uploadedFile.getFileUUID();
//            } catch (IOException e) {
//                throw new FileUploadFailException(e);
//            }
//        }
//
//        routeDao.insertRoute(new RouteDto(owner, routeForm.getName(), routeForm.getOverview(), fileUUID, routeForm.getStartDate()));
//    }
//
//    @Override
//    public List<RouteDto> findUserRoutes(int owner) {
//        return routeDao.findRouteDtoByOwner(owner);
//    }
//
//    @Override
//    public void updateRoute(int seq, int owner, RouteForm routeForm) {
//        RouteDto routeDto = routeDao.findRouteDtoBySeq(seq)
//                .filter(r -> r.getOwner().equals(owner))
//                .orElseThrow(UserAuthException::new);
//
//        String fileUUID = null;
//        if (routeForm.getThumbnail() != null) {
//            try {
//                fileUploadService.deleteImage(routeDto.getThumbnail());
//                UploadedFile uploadedFile = fileUploadService.uploadImage(routeForm.getThumbnail());
//                fileUUID = uploadedFile.getFileUUID();
//            } catch (IOException e) {
//                throw new FileUploadFailException(e);
//            }
//        }
//
//        routeDto =
//            new RouteDto(seq, owner, routeForm.getName(), routeForm.getOverview(), fileUUID, routeForm.getStartDate());
//
//        routeDao.updateRoute(routeDto);
//    }
//
//    @Override
//    public void deleteRoute(int owner, int routeSeq) {
//        routeDao.findRouteDtoBySeq(routeSeq)
//                .filter(r -> r.getOwner().equals(owner))
//                .orElseThrow(UserAuthException::new);
//
//        routeDao.deleteRouteBySeq(routeSeq);
//    }
//}
