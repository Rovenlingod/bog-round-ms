package com.example.bogroundms.repository;

import com.example.bogroundms.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface RoundRepository extends PagingAndSortingRepository<Round, UUID> {
    List<Round> findAllByIdIn(List<UUID> ids);
}
