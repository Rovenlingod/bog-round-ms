package com.example.bogroundms.utulities;

import com.example.bogroundms.feign.feignDtos.UserDetailsDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CustomUtils {

    public static Optional<UserDetailsDTO> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsDTO) {
            return Optional.of((UserDetailsDTO) principal);
        } else {
            return Optional.empty();
        }
    }
}
