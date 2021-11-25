package com.example.bogroundms.repository;

import com.example.bogroundms.domain.RoundExercise;
import com.example.bogroundms.domain.RoundExerciseKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundExerciseRepository extends JpaRepository<RoundExercise, RoundExerciseKey> {
}
