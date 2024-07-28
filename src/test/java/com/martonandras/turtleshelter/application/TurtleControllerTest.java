package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.application.dto.CreateTurtleRequestDto;
import com.martonandras.turtleshelter.application.dto.TurtleResponseDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TurtleControllerTest {

    @Mock
    TurtleServiceImpl service;

    @InjectMocks
    TurtleControllerImpl controller;

    @Test
    public void testCreateTurtleStatusOk() {
        //given
        CreateTurtleRequestDto requestDto = initCreateTurtleRequestDto();
        TurtleResponseDto responseDto = initTurtleResponseDto();
        when(service.createTurtle(Mockito.any())).thenReturn(Optional.of(responseDto));

        //when
        ResponseEntity<TurtleResponseDto> actual = controller.createTurtle(requestDto);

        //then
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testUpdateTurtleStatusOk() {
        //given
        UpdateTurtleRequestDto requestDto = initUpdateTurtleRequestDto();
        TurtleResponseDto responseDto = initTurtleResponseDto();
        when(service.updateTurtle(Mockito.anyLong(), Mockito.any())).thenReturn(Optional.of(responseDto));

        //when
        ResponseEntity<TurtleResponseDto> actual = controller.updateTurtle(1L, requestDto);

        //then
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testGetTurtleInfoStatusOk() {
        //given
        TurtleResponseDto responseDto = initTurtleResponseDto();
        when(service.getTurtleInfo(Mockito.anyLong())).thenReturn(Optional.of(responseDto));

        //when
        ResponseEntity<TurtleResponseDto> actual = controller.getTurtle(1L);

        //then
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testListAllTurtlesInfoStatusOk() {
        //given
        when(service.listAllActiveTurtles(Mockito.any())).thenReturn(Page.empty());

        //when
        ResponseEntity<Page<TurtleResponseDto>> actual = controller.listTurtles(Pageable.ofSize(1), null);

        //then
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testListTurtlesBySpeciesInfoStatusOk() {
        //given
        when(service.listTurtlesBySpecies(Mockito.any(), Mockito.anyLong())).thenReturn(Page.empty());

        //when
        ResponseEntity<Page<TurtleResponseDto>> actual = controller.listTurtles(Pageable.ofSize(1), 1L);

        //then
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testDeleteTurtleStatusOk() {
        //given
        TurtleResponseDto responseDto = initTurtleResponseDto();
        when(service.deleteTurtle(Mockito.anyLong())).thenReturn(Optional.of(responseDto));

        //when
        ResponseEntity<TurtleResponseDto> actual = controller.deleteTurtle(1L);

        //then
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    private CreateTurtleRequestDto initCreateTurtleRequestDto() {
        return CreateTurtleRequestDto.builder()
                .speciesId(1L)
                .name("testTurtle")
                .arrivalDate(ZonedDateTime.now())
                .weight(120L)
                .measurementUnitOfWeight("kg")
                .age(3L)
                .build();
    }

    private UpdateTurtleRequestDto initUpdateTurtleRequestDto() {
        return UpdateTurtleRequestDto.builder()
                .speciesId(2L)
                .build();
    }

    private TurtleResponseDto initTurtleResponseDto() {
        return TurtleResponseDto.builder()
                .id(1L)
                .speciesId(1L)
                .name("testTurtle")
                .arrivalDate(ZonedDateTime.now())
                .weight(120L)
                .age(3L)
                .build();
    }
}
