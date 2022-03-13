package com.example.bogroundms.service;

import com.example.bogroundms.dto.RoundCreationRequest;
import com.example.bogroundms.dto.RoundDTO;

import java.util.List;
import java.util.UUID;

public interface RoundService {
    UUID createRound(RoundCreationRequest roundCreationRequest);
    List<RoundDTO> getRoundsByIds(List<String> ids);
}
