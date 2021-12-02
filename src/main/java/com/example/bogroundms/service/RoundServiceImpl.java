package com.example.bogroundms.service;

import com.example.bogroundms.domain.Round;
import com.example.bogroundms.domain.RoundExercise;
import com.example.bogroundms.dto.RoundCreationRequest;
import com.example.bogroundms.dto.RoundDTO;
import com.example.bogroundms.exception.RoundException;
import com.example.bogroundms.mapper.RoundMapper;
import com.example.bogroundms.repository.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoundServiceImpl implements RoundService {

    private RoundRepository roundRepository;
    private RoundMapper roundMapper;

    public RoundServiceImpl(RoundRepository roundRepository, RoundMapper roundMapper) {
        this.roundRepository = roundRepository;
        this.roundMapper = roundMapper;
    }

    @Override
    public UUID createRound(RoundCreationRequest roundCreationRequest) {
        Round newRound = roundMapper.toEntity(roundCreationRequest);
        for (RoundExercise e :
                newRound.getRoundExercises()) {
            e.setRound(newRound);
        }
        return roundRepository.save(newRound).getId();
    }

    @Override
    public List<RoundDTO> getRoundsByIds(List<String> ids) {
        List<Round> rounds = roundRepository
                .findAllByIdIn(ids.stream().map(UUID::fromString).collect(Collectors.toList()));
        if (rounds.isEmpty()) throw new RoundException("Exercises from provided list are non-existent or are not for public use");
        return roundMapper.toDtos(rounds);
    }
}
