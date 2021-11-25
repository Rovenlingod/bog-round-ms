package com.example.bogroundms.domain;

import java.io.Serializable;
import java.util.UUID;

public class RoundExerciseKey implements Serializable {

    private UUID round;
    private UUID exerciseId;

    public UUID getRound() {
        return round;
    }

    public void setRound(UUID round) {
        this.round = round;
    }

    public UUID getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(UUID exerciseId) {
        this.exerciseId = exerciseId;
    }
}
