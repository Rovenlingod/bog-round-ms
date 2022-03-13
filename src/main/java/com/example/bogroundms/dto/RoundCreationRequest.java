package com.example.bogroundms.dto;

import com.example.bogroundms.enums.RoundType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundCreationRequest {
    private RoundType roundType;
    private List<String> exerciseIds;
}
