package com.example.bogroundms.controller;

import com.example.bogroundms.dto.RoundCreationRequest;
import com.example.bogroundms.dto.RoundDTO;
import com.example.bogroundms.service.RoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Validated
public class RoundController {

    private RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping("/round")
    public ResponseEntity<Object> createRound(@RequestBody RoundCreationRequest roundCreationRequest) {
        UUID id = roundService.createRound(roundCreationRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id.toString()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/rounds")
    public ResponseEntity<List<String>> createRoundList(@RequestBody List<RoundCreationRequest> roundCreationRequestList) {
        List<String> roundIds = new ArrayList<>();
        for (RoundCreationRequest roundCreationRequest : roundCreationRequestList)
            roundIds.add(roundService.createRound(roundCreationRequest).toString());
        return ResponseEntity.ok().body(roundIds);
    }

    @GetMapping("/round/{ids}")
    public ResponseEntity<List<RoundDTO>> getRoundsByIds(@PathVariable("ids") List<@Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$", message = "some of the provided uuids have invalid format") String> ids) {
        return ResponseEntity.ok().body(roundService.getRoundsByIds(ids));
    }
}
