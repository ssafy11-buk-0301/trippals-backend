package com.ssafy.trippals.route.service;

import com.ssafy.trippals.attraction.dao.AttractionDao;
import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.ContentType;
import com.ssafy.trippals.attraction.dto.NearByAttractionContentTypeSelect;
import com.ssafy.trippals.attraction.dto.RouteAttractionDto;
import com.ssafy.trippals.common.exception.AttractionNotFoundException;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dao.RouteDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteAttractionServiceImpl implements RouteAttractionService {
    private final RouteDao routeDao;
    private final AttractionDao attractionDao;

    @Override
    public List<AttractionDto> getRouteAttractions(int userSeq, int routeSeq) {
        routeDao.findRouteDtoBySeq(routeSeq)
                .filter(route -> route.getOwner().equals(userSeq))
                .orElseThrow(UserAuthException::new);

        return attractionDao.findByRouteSeq(routeSeq).stream()
                .map(AttractionDto::new)
                .toList();
    }

    @Override
    public void addRouteAttraction(int userSeq, int routeSeq, int contentId) {
        routeDao.findRouteDtoBySeq(routeSeq)
                .filter(route -> route.getOwner().equals(userSeq))
                .orElseThrow(UserAuthException::new);

        routeDao.insertAttractionIntoRoute(routeSeq, contentId);
    }

    @Override
    public void deleteRouteAttraction(int userSeq, int routeSeq, int contentId) {
        routeDao.findRouteDtoBySeq(routeSeq)
                .filter(route -> route.getOwner().equals(userSeq))
                .orElseThrow(UserAuthException::new);

        routeDao.deleteAttractionFromRoute(routeSeq, contentId);
    }

    @Override
    public void changeRouteAttraction(int userSeq, int routeSeq, int from, int to) {
        routeDao.findRouteDtoBySeq(routeSeq)
                .filter(route -> route.getOwner().equals(userSeq))
                .orElseThrow(UserAuthException::new);

        Optional<RouteAttractionDto> fromAttraction = routeDao.findRouteAttractionDtoByRouteSeqAndContentId(routeSeq, from);
        Optional<RouteAttractionDto> toAttraction = routeDao.findRouteAttractionDtoByRouteSeqAndContentId(routeSeq, to);

        if (fromAttraction.isEmpty() || toAttraction.isEmpty()) { throw new AttractionNotFoundException(); }

        routeDao.updateRouteAttractionOrder(routeSeq, from, toAttraction.get().getOrderNumber());
        routeDao.updateRouteAttractionOrder(routeSeq, to, fromAttraction.get().getOrderNumber());
    }

    @Override
    public PageResponse<AttractionDto> getNearbyRouteAttractions(int userSeq, int routeSeq, PageParams pageParams, ContentType contentType) {
        routeDao.findRouteDtoBySeq(routeSeq)
                .filter(route -> route.getOwner().equals(userSeq))
                .orElseThrow(UserAuthException::new);

        NearByAttractionContentTypeSelect attractionSelect = new NearByAttractionContentTypeSelect(routeSeq, pageParams, contentType);
        List<AttractionDto> contents =
                attractionDao.findNearbyAttractionsByContentType(attractionSelect);

        int totalContents = attractionDao.countNearbyAttractionsByContentType(attractionSelect);

        return new PageResponse<>(contents, pageParams, totalContents);
    }
}
