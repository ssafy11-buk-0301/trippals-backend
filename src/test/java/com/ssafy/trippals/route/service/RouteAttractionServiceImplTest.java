package com.ssafy.trippals.route.service;

import com.ssafy.trippals.attraction.dao.AttractionDao;
import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.ContentType;
import com.ssafy.trippals.attraction.dto.RouteAttractionDto;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteAttractionServiceImplTest {
    @InjectMocks
    RouteAttractionServiceImpl routeAttractionService;

    @Mock RouteDao routeDao;
    @Mock AttractionDao attractionDao;

    static int owner = 1;

    @BeforeEach
    void setUp() {
        when(routeDao.findRouteDtoBySeq(any())).thenReturn(Optional.of(new RouteDto(owner, null, null, null, null)));
    }

    @Test
    void getRouteAttractions() {
        // given
        int routeSeq = 1;
        List<RouteAttractionDto> routeAttractions = List.of(
                new RouteAttractionDto(1, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.TEN, 1),
                new RouteAttractionDto(2, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.TEN, 1),
                new RouteAttractionDto(3, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.TEN, 1),
                new RouteAttractionDto(4, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.TEN, 1),
                new RouteAttractionDto(5, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.TEN, 1)
        );
        when(attractionDao.findByRouteSeq(routeSeq)).thenReturn(routeAttractions);

        // when
        List<AttractionDto> actual = routeAttractionService.getRouteAttractions(owner, routeSeq);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getContentTypeId().equals(1));
    }

    @Test
    void addRouteAttraction() {
        routeAttractionService.addRouteAttraction(owner, 1, 1);
    }

    @Test
    void deleteRouteAttraction() {
        routeAttractionService.deleteRouteAttraction(owner, 1, 1);
    }

    @Test
    void changeRouteAttraction() {
        // given
        int from = 1;
        RouteAttractionDto fromDto = new RouteAttractionDto(from, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.ONE, from);
        int to = 2;
        RouteAttractionDto toDto = new RouteAttractionDto(to, 1, "test", "test", "test", "test", BigDecimal.ONE, BigDecimal.ONE, to);
        when(routeDao.findRouteAttractionDtoByRouteSeqAndContentId(1, from))
                .thenReturn(Optional.of(fromDto));
        when(routeDao.findRouteAttractionDtoByRouteSeqAndContentId(1, to))
                .thenReturn(Optional.of(toDto));

        int[] expected = new int[3];

        when(routeDao.updateRouteAttractionOrder(any(), any(), any()))
                .thenAnswer(invocation -> {
                    expected[invocation.getArgument(1, Integer.class)] = invocation.getArgument(2, Integer.class);
                    return 1;
                });

        // when
        routeAttractionService.changeRouteAttraction(owner, 1, from, to);

        // then
        assertThat(expected[1]).isEqualTo(2);
        assertThat(expected[2]).isEqualTo(1);
    }

    @Test
    void getNearbyRouteAttractions() {
        // given
        when(attractionDao.findNearbyAttractionsByContentType(any())).thenReturn(List.of(new AttractionDto()));
        when(attractionDao.countNearbyAttractionsByContentType(any())).thenReturn(1000);
        int routeSeq = 1;
        PageParams pageParams = new PageParams(0, 100, "");

        // when
        PageResponse<AttractionDto> response = routeAttractionService.getNearbyRouteAttractions(owner, routeSeq, pageParams, ContentType.ATTRACTION);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getContents()).isNotEmpty();
        assertThat(response.getSize()).isEqualTo(1);
        assertThat(response.getPage()).isEqualTo(1);
        assertThat(response.getTotalPage()).isEqualTo(10);
        assertThat(response.getTotalContents()).isEqualTo(1000);
    }
}