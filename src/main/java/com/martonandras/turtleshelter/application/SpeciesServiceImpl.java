package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.domain.model.SpeciesRepository;
import com.martonandras.turtleshelter.infrastructure.validation.exception.TurtleProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpeciesServiceImpl implements SpeciesService {

    @Autowired
    private final SpeciesRepository speciesRepository;

    @Override
    public boolean throwExceptionIfSpeciesNotFound(Long speciesId) {

        if (!speciesRepository.existsById(speciesId)) {
            throw new TurtleProcessingException(String.format(TurtleProcessingException.SPECIES_NOT_FOUND, speciesId));
        }

        return true;
    }
}
