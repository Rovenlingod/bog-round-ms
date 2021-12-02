package com.example.bogroundms.controller;

import com.example.bogroundms.dto.RoundCreationRequest;
import com.example.bogroundms.dto.RoundDTO;
import com.example.bogroundms.feign.ExerciseServiceFeign;
import com.example.bogroundms.feign.feignDtos.ExerciseDTO;
import com.example.bogroundms.feign.feignDtos.PageDTO;
import com.example.bogroundms.service.RoundService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/round")
@Validated
public class RoundController {

    private RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping
    public ResponseEntity<Object> createRound(@RequestBody RoundCreationRequest roundCreationRequest) {
        UUID id = roundService.createRound(roundCreationRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id.toString()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{ids}")
    public ResponseEntity<List<RoundDTO>> getRoundsByIds(@PathVariable("ids") List<@Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$", message = "some of the provided uuids have invalid format") String> ids) {
        return ResponseEntity.ok().body(roundService.getRoundsByIds(ids));
    }

}
