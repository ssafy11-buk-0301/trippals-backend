package com.ssafy.trippals.route.service;

import com.ssafy.trippals.common.exception.RouteLimitExceededException;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.route.dto.RouteForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {
    @InjectMocks
    RouteServiceImpl routeService;
    @Mock RouteDao routeDao;
    static int owner;

    @Test
    void createRoute() {
        // given
        List<RouteDto> routeDtos = List.of(
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now()),
                new RouteDto(1, 1, "test", "test", "test", now())
        );
        when(routeDao.findRouteDtoByOwner(any())).thenReturn(routeDtos);

        // then
        assertThatNoException().isThrownBy(() -> routeService.createRoute(routeDtos.get(0).getOwner(), new RouteForm()));
    }

    @Test
    void createRouteFail() {
        // given
        List<RouteDto> routeDtos = List.of(
                new RouteDto(), new RouteDto(), new RouteDto(), new RouteDto(),
                new RouteDto(), new RouteDto(), new RouteDto(), new RouteDto(), new RouteDto(),
                new RouteDto(), new RouteDto(), new RouteDto(), new RouteDto(), new RouteDto()
        );
        when(routeDao.findRouteDtoByOwner(null)).thenReturn(routeDtos);

        // then
        assertThatThrownBy(() -> routeService.createRoute(0, new RouteForm()))
                .isInstanceOf(RouteLimitExceededException.class);
    }

    @Test
    void updateAndDeleteRoute() {
        // given
        RouteDto routeDto = new RouteDto(1, 1, "test", "test", "test", now());
        RouteDto expected = new RouteDto(1, 1, "test", "test", "test", now());
        when(routeDao.findRouteDtoBySeq(1)).thenReturn(Optional.of(expected));

        // then
        assertThatNoException().isThrownBy(() -> routeService.updateRoute(routeDto.getSeq(), routeDto.getOwner(), null));
        assertThatNoException().isThrownBy(() -> routeService.updateRoute(routeDto.getSeq(), routeDto.getOwner(), null));
    }

    @Test
    void updateAndDeleteRouteFail() {
        // given
        RouteDto routeDto = new RouteDto(1, 1, "test", "test", "test", now());
        RouteDto expected = new RouteDto(1, 2, "test", "test", "test", now());
        when(routeDao.findRouteDtoBySeq(1)).thenReturn(Optional.of(expected));

        // then
        assertThatThrownBy(() -> routeService.updateRoute(routeDto.getSeq(), routeDto.getOwner(), null)).isInstanceOf(UserAuthException.class);
        assertThatThrownBy(() -> routeService.deleteRoute(1, 1)).isInstanceOf(UserAuthException.class);
    }
}