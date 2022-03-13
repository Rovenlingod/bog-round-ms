package com.example.bogroundms.feign.feignDtos;

import com.example.bogroundms.enums.MuscleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseCreationRequest {
    private String title;
    private Boolean isPublic;
    private MultipartFile mediaFile;
    private Long countdownInSeconds;
    private MuscleType muscleType;
    private String creatorId;
    private List<String> equipmentIds;
}
