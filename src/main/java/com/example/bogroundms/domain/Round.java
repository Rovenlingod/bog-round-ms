package com.example.bogroundms.domain;

import com.example.bogroundms.enums.RoundType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "round")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @Id
    @Column(name = "round_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "round_type")
    private RoundType roundType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "round", cascade = CascadeType.ALL)
    private List<RoundExercise> roundExercises;
}
