package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.application.dto.TurtleResponseDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import com.martonandras.turtleshelter.domain.model.Turtle;
import com.martonandras.turtleshelter.domain.model.TurtleRepository;
import com.martonandras.turtleshelter.infrastructure.validation.exception.TurtleProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link TurtleServiceImpl}
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@RunWith(MockitoJUnitRunner.class)
public class TurtleServiceTest {

    @Mock
    TurtleRepository turtleRepository;

    @InjectMocks
    TurtleServiceImpl turtleService;

    @Mock
    SpeciesServiceImpl speciesService;

    @Test(expected = TurtleProcessingException.class)
    public void testUpdateTurtle_turtleNotFound() {
        //given
        UpdateTurtleRequestDto requestDto = initUpdateTurtleRequestDto();
        when(turtleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //when
        turtleService.updateTurtle(1L, requestDto);
    }

    @Test(expected = TurtleProcessingException.class)
    public void testUpdateTurtle_turtleDeleted() {
        //given
        UpdateTurtleRequestDto requestDto = initUpdateTurtleRequestDto();
        when(turtleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Turtle.builder().isDeleted(true).build()));

        //when
        turtleService.updateTurtle(1L, requestDto);
    }

    @Test
    public void testUpdateTurtleMerged() {
        //given
        Turtle existingTurtle = createTurtle();
        UpdateTurtleRequestDto requestDto = initUpdateTurtleRequestDto();

        when(turtleRepository.findById(Mockito.any())).thenReturn(Optional.of(existingTurtle));
        when(speciesService.throwExceptionIfSpeciesNotFound(Mockito.anyLong())).thenReturn(true);

        //when
        Optional<TurtleResponseDto> actual = turtleService.updateTurtle(1L, requestDto);

        //then
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(existingTurtle.getName(), actual.get().getName());
        Assert.assertEquals(requestDto.getSpeciesId(), actual.get().getSpeciesId());
    }

    private Turtle createTurtle() {
        return Turtle.builder()
                .id(1L)
                .speciesId(1L)
                .name("turtle1")
                .isDeleted(false)
                .age(2L)
                .weight(123L)
                .arrivalDate(ZonedDateTime.now())
                .build();
    }

    private UpdateTurtleRequestDto initUpdateTurtleRequestDto() {
        return UpdateTurtleRequestDto.builder()
                .speciesId(2L)
                .build();
    }
}
