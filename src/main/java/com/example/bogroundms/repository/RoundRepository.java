package com.example.bogroundms.repository;

import com.example.bogroundms.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoundRepository extends JpaRepository<Round, UUID> {
}
