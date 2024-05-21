package com.ssafy.trippals.route.service;

import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dao.RouteEditorDao;
import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.route.dto.RouteEditorDto;
import com.ssafy.trippals.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteEditorServiceImpl implements RouteEditorService {
    private final RouteDao routeDao;
    private final RouteEditorDao routeEditorDao;

    @Override
    public List<UserDto> findAllEditors(int userSeq, int routeSeq) {
        if (!canEdit(routeSeq, userSeq)) throw new UserAuthException();

        return routeEditorDao.findEditorByRouteSeq(routeSeq);
    }

    @Override
    public PageResponse<RouteDto> findAllEditableRoutes(int userSeq, PageParams pageParams) {
        List<RouteDto> contents = routeEditorDao.findRouteByEditorSeq(userSeq, pageParams.getOffset(), pageParams.getLimit());
        int contentsSize = routeEditorDao.countRouteByEditorSeq(userSeq);
        return new PageResponse<>(contents, pageParams, contentsSize);
    }

    @Override
    public boolean addEditor(int routeSeq, int owner, int editor) {
        if (!isOwner(routeSeq, owner)) throw new UserAuthException();

        int modified = routeEditorDao.insertRouteEditor(new RouteEditorDto(routeSeq, editor));

        return modified == 1;
    }

    @Override
    public boolean removeEditor(int routeSeq, int owner, int editor) {
        if (!isOwner(routeSeq, owner)) throw new UserAuthException();

        int modified = routeEditorDao.deleteRouteEditor(routeSeq, editor);

        return modified == 1;
    }

    @Override
    public boolean canEdit(int routeSeq, int userSeq) {
        return isOwner(routeSeq, userSeq) || isEditor(routeSeq, userSeq);
    }

    @Override
    public boolean isEditor(int routeSeq, int userSeq) {
        return routeEditorDao.findEditorByRouteSeq(routeSeq).stream()
                .anyMatch(u -> u.getSeq() == userSeq);
    }

    @Override
    public boolean isOwner(int routeSeq, int userSeq) {
        return routeDao.findRouteDtoBySeq(routeSeq)
                .filter(r -> r.getOwner() == userSeq)
                .isPresent();
    }
}
