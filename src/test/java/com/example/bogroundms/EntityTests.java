package com.example.bogroundms;

import com.example.bogroundms.domain.Round;
import com.example.bogroundms.domain.RoundExercise;
import com.example.bogroundms.repository.RoundExerciseRepository;
import com.example.bogroundms.repository.RoundRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EntityTests {

    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private RoundExerciseRepository roundExerciseRepository;

    @Test
    public void testRoundEntity() {
        Round round = new Round();
        RoundExercise roundExercise1 = new RoundExercise();
        roundExercise1.setRound(round);
        roundExercise1.setExerciseId(UUID.randomUUID());
        Round result = roundRepository.save(round);
        roundExerciseRepository.save(roundExercise1);
        RoundExercise roundExerciseResult = roundExerciseRepository.findAll().get(0);
        Assert.assertEquals(roundExerciseResult.getRound().getId(), result.getId());
    }
}
