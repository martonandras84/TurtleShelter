package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.application.dto.CreateTurtleRequestDto;
import com.martonandras.turtleshelter.application.dto.TurtleResponseDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/turtle")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TurtleControllerImpl implements TurtleController {

    private final TurtleService turtleService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TurtleResponseDto> getTurtle(@PathVariable("id") Long turtleId) {
        return ResponseEntity.of(turtleService.getTurtleInfo(turtleId));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<TurtleResponseDto>> listTurtles(
            Pageable pageable,
            @RequestParam(name = "speciesId", required = false) Long speciesId) {

        if (ObjectUtils.isEmpty(speciesId)) {
            return ResponseEntity.ok(turtleService.listAllActiveTurtles(pageable));
        }

        return ResponseEntity.ok(turtleService.listTurtlesBySpecies(pageable, speciesId));
    }

    @Override
    @PostMapping
    public ResponseEntity<TurtleResponseDto> createTurtle(@RequestBody CreateTurtleRequestDto requestDto) {
        return ResponseEntity.of(turtleService.createTurtle(requestDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TurtleResponseDto> updateTurtle(@PathVariable("id") @NonNull Long turtleId, @RequestBody UpdateTurtleRequestDto requestDto) {
        return ResponseEntity.of(turtleService.updateTurtle(turtleId, requestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<TurtleResponseDto> deleteTurtle(@PathVariable("id") Long turtleId) {
        return ResponseEntity.of(turtleService.deleteTurtle(turtleId));
    }
}
