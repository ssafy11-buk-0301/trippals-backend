package com.ssafy.trippals.route.service;

import com.ssafy.trippals.common.exception.RouteLimitExceededException;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteInfo;
import com.ssafy.trippals.route.dto.RouteInsert;
import com.ssafy.trippals.route.dto.RouteInsertInfo;
import com.ssafy.trippals.route.dto.RouteUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteServiceImpl implements RouteService {
    static final int MAX_ROUTE_COUNT = 10;
    private final RouteDao routeDao;

    @Override
    public void createRoute(RouteInsertInfo routeInsertInfo) {
        if (routeDao.findRouteDataByOwner(routeInsertInfo.getOwner()).size() >= MAX_ROUTE_COUNT) {
            throw new RouteLimitExceededException(MAX_ROUTE_COUNT);
        }
        routeDao.insertRoute(new RouteInsert(routeInsertInfo));
    }

    @Override
    public List<RouteInfo> findUserRoutes(int owner) {
        return routeDao.findRouteDataByOwner(owner).stream()
                .map(RouteInfo::new)
                .toList();
    }

    @Override
    public void updateRoute(RouteInfo routeInfo) {
        routeDao.findRouteDataBySeq(routeInfo.getSeq())
                .filter(r -> r.getOwner().equals(routeInfo.getOwner()))
                .orElseThrow(UserAuthException::new);

        routeDao.updateRoute(new RouteUpdate(routeInfo));
    }

    @Override
    public void deleteRoute(int owner, int routeSeq) {
        routeDao.findRouteDataBySeq(routeSeq)
                .filter(r -> r.getOwner().equals(owner))
                .orElseThrow(UserAuthException::new);

        routeDao.deleteRouteBySeq(routeSeq);
    }
}
