package com.example.bogroundms.mapper;

import com.example.bogroundms.domain.Round;
import com.example.bogroundms.domain.RoundExercise;
import com.example.bogroundms.dto.RoundCreationRequest;
import com.example.bogroundms.dto.RoundDTO;
import com.example.bogroundms.exception.RoundCreationException;
import com.example.bogroundms.feign.ExerciseServiceFeign;
import com.example.bogroundms.feign.feignDtos.ExerciseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
public abstract class RoundMapper {

    @Autowired
    private ExerciseServiceFeign exerciseService;

    @Mappings({
            @Mapping(target = "roundType", source = "roundType"),
            @Mapping(target = "roundExercises", expression = "java(this.map(roundCreationRequest.getExerciseIds()))")
    })
    public abstract Round toEntity(RoundCreationRequest roundCreationRequest);

    @Mappings({
            @Mapping(target = "id", expression = "java(round.getId().toString())"),
            @Mapping(target = "exercises", expression = "java(this.roundExercisesToExercises(round.getRoundExercises()))")
    })
    public abstract RoundDTO toDto(Round round);

    public abstract List<RoundDTO> toDtos(List<Round> rounds);

    protected List<RoundExercise> map(List<String> exerciseIds) {
        if (exerciseIds == null || exerciseIds.isEmpty()) {
            throw new RoundCreationException("You must provide at least one exercise.");
        }
        List<ExerciseDTO> exercises = exerciseService.getExercisesByIds(exerciseIds);
        if (exercises == null || exercises.isEmpty()) {
            throw new RoundCreationException("Exercises from provided ids are non-existent or private. You must provide at least one exercise.");
        }
        return exercises.stream().map(e -> {
            RoundExercise result = new RoundExercise();
            result.setExerciseId(UUID.fromString(e.getId()));
            return result;
        }).collect(Collectors.toList());
    }

    protected List<ExerciseDTO> roundExercisesToExercises(List<RoundExercise> roundExercises) {
        List<String> exerciseIds = roundExercises.stream().map(e -> e.getExerciseId().toString()).collect(Collectors.toList());
        return exerciseService.getExercisesByIds(exerciseIds);
    }

}
