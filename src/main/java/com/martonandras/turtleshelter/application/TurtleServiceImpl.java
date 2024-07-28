package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.application.dto.CreateTurtleRequestDto;
import com.martonandras.turtleshelter.application.dto.TurtleResponseDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import com.martonandras.turtleshelter.domain.model.Turtle;
import com.martonandras.turtleshelter.domain.model.TurtleRepository;
import com.martonandras.turtleshelter.infrastructure.validation.exception.InvalidWeightException;
import com.martonandras.turtleshelter.infrastructure.validation.exception.TurtleProcessingException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service implementation of {@link TurtleService}
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TurtleServiceImpl implements TurtleService {

    @Autowired
    private final TurtleRepository turtleRepository;

    @Autowired
    private final SpeciesService speciesService;

    @Override
    public Page<TurtleResponseDto> listAllActiveTurtles(Pageable pageable) {
        return turtleRepository.findAllByIsDeletedFalse(pageable).map(TurtleResponseDto::of);
    }

    @Override
    public Page<TurtleResponseDto> listTurtlesBySpecies(Pageable pageable, Long speciesId) {
        return turtleRepository.findAllBySpeciesId(pageable, speciesId).map(TurtleResponseDto::of);
    }

    @Override
    public Optional<TurtleResponseDto> getTurtleInfo(Long turtleId) {
        return turtleRepository.findById(turtleId).map(TurtleResponseDto::of);
    }

    @Override
    public Optional<TurtleResponseDto> createTurtle(CreateTurtleRequestDto requestDto) {
        Optional<Turtle> existingTurtleOpt = turtleRepository.findByName(requestDto.getName());
        if (existingTurtleOpt.isPresent()) {
            return Optional.of(TurtleResponseDto.of(existingTurtleOpt.get()));
        }

        speciesService.throwExceptionIfSpeciesNotFound(requestDto.getSpeciesId());

        Turtle newTurtle = Turtle.of(requestDto);
        turtleRepository.save(newTurtle);
        return Optional.of(TurtleResponseDto.of(newTurtle));
    }

    @Override
    @Transactional
    public Optional<TurtleResponseDto> updateTurtle(Long turtleId, UpdateTurtleRequestDto requestDto) {

        Turtle existingTurtle = getValidTurtleOrThrowException(turtleId);

        if (!ObjectUtils.isEmpty(requestDto.getSpeciesId())) {
            speciesService.throwExceptionIfSpeciesNotFound(requestDto.getSpeciesId());
        }

        if ((ObjectUtils.isEmpty(requestDto.getWeight()) && !ObjectUtils.isEmpty(requestDto.getMeasurementUnitOfWeight()))
        || (!ObjectUtils.isEmpty(requestDto.getWeight()) && ObjectUtils.isEmpty(requestDto.getMeasurementUnitOfWeight()))) {
            throw new InvalidWeightException(InvalidWeightException.UNIT_OR_VALUE_MISSING);
        }

        existingTurtle.merge(Turtle.of(requestDto));
        return Optional.of(TurtleResponseDto.of(existingTurtle));
    }

    @Override
    @Transactional
    public Optional<TurtleResponseDto> deleteTurtle(Long turtleId) {

        Turtle existingTurtle = getValidTurtleOrThrowException(turtleId);

        existingTurtle.deleteTurtle();
        return Optional.of(TurtleResponseDto.of(existingTurtle));
    }

    private Turtle getValidTurtleOrThrowException(Long turtleId) {
        Optional<Turtle> existingTurtleOpt = turtleRepository.findById(turtleId);
        if (existingTurtleOpt.isEmpty()) {
            throw new TurtleProcessingException(String.format(TurtleProcessingException.TURTLE_NOT_FOUND, turtleId));
        }

        Turtle existingTurtle = existingTurtleOpt.get();

        if (existingTurtle.isDeleted()) {
            throw new TurtleProcessingException(String.format(TurtleProcessingException.TURTLE_ALREADY_DELETED, turtleId));
        }

        return existingTurtle;
    }
}
