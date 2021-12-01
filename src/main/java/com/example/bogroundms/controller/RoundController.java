package com.example.bogroundms.controller;

import com.example.bogroundms.feign.ExerciseServiceFeign;
import com.example.bogroundms.feign.feignDtos.ExerciseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/round")
public class RoundController {

    private ExerciseServiceFeign exerciseServiceFeign;

    public RoundController(ExerciseServiceFeign exerciseServiceFeign) {
        this.exerciseServiceFeign = exerciseServiceFeign;
    }

    @GetMapping("/test")
    public ResponseEntity<Page<ExerciseDTO>> test() {
        return ResponseEntity.ok().body(exerciseServiceFeign.getAllPageable(0, 5));
    }
}
