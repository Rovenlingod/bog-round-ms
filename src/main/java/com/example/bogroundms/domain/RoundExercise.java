package com.example.bogroundms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@IdClass(value = RoundExerciseKey.class)
@Table(name = "round_exercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundExercise {

    @Id
    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;
    @Id
    private UUID exerciseId;

}
