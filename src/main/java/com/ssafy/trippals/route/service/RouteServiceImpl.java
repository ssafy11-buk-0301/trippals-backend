package com.ssafy.trippals.route.service;

import com.ssafy.trippals.common.exception.RouteLimitExceededException;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteDto;
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
    public void createRoute(RouteDto routeDto) {
        if (routeDao.findRouteDtoByOwner(routeDto.getOwner()).size() >= MAX_ROUTE_COUNT) {
            throw new RouteLimitExceededException(MAX_ROUTE_COUNT);
        }
        routeDao.insertRoute(routeDto);
    }

    @Override
    public List<RouteDto> findUserRoutes(int owner) {
        return routeDao.findRouteDtoByOwner(owner).stream()
                .toList();
    }

    @Override
    public void updateRoute(RouteDto routeDto) {
        routeDao.findRouteDtoBySeq(routeDto.getSeq())
                .filter(r -> r.getOwner().equals(routeDto.getOwner()))
                .orElseThrow(UserAuthException::new);

        routeDao.updateRoute(routeDto);
    }

    @Override
    public void deleteRoute(int owner, int routeSeq) {
        routeDao.findRouteDtoBySeq(routeSeq)
                .filter(r -> r.getOwner().equals(owner))
                .orElseThrow(UserAuthException::new);

        routeDao.deleteRouteBySeq(routeSeq);
    }
}
