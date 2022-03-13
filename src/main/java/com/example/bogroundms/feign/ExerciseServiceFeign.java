package com.example.bogroundms.feign;

import com.example.bogroundms.feign.feignDtos.ExerciseCreationRequest;
import com.example.bogroundms.feign.feignDtos.ExerciseDTO;
import com.example.bogroundms.feign.feignDtos.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "exercise-service")
public interface ExerciseServiceFeign {

    @PostMapping("/api/exercise")
    Object saveExercise(ExerciseCreationRequest exerciseCreationRequest);

    @GetMapping(value = "/api/exercise/{ids}")
    List<ExerciseDTO> getExercisesByIds(@PathVariable(name = "ids") List<String> ids);

    @GetMapping("/api/exercise")
    PageDTO<ExerciseDTO> getAllPageable(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize);
}
