package com.example.bogroundms.logging;

import com.example.bogroundms.dto.RoundDTO;
import com.example.bogroundms.feign.feignDtos.UserDetailsDTO;
import com.example.bogroundms.utulities.CustomUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    @Pointcut("within(com.example.bogroundms..*)")
    protected void loggingAllOperations() {
    }

    @Pointcut("execution(* com.example.bogroundms.controller.*.*(..))")
    protected void loggingControllerOperations() {
    }

    @AfterThrowing(pointcut = "loggingAllOperations()", throwing = "exception")
    public void logAllExceptions(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception occurred while executing method \"" + joinPoint.getSignature() + "\"");
        log.error("Cause: " + exception.getCause() + ". Exception : " + exception.getClass().getName() + ". Message: " + exception.getMessage());
        log.error("Provided arguments: ");
        Object[] arguments = joinPoint.getArgs();
        for (Object a :
                arguments) {
            if (Objects.isNull(a)) {
                log.error("[null]");
            } else {
                log.error("Class name: " + a.getClass().getSimpleName() + ". Value: " + a);
            }
        }
    }

    @Before("loggingControllerOperations()")
    public void logEndpointAccess(JoinPoint joinPoint) {
        Optional<UserDetailsDTO> currentUser = CustomUtils.getCurrentUser();
        String username = currentUser.isPresent() ? currentUser.get().getUsername() : "Anonymous";
        log.info("User with username \"" + username + "\" got access to the controller method " + joinPoint.getSignature());
    }

    @AfterReturning(value = "execution(* com.example.bogroundms.service.RoundService.createRound(..))", returning = "id")
    public void logRoundCreation(JoinPoint joinPoint, UUID id) {
        Optional<UserDetailsDTO> currentUser = CustomUtils.getCurrentUser();
        String username = currentUser.isPresent() ? currentUser.get().getUsername() : "Anonymous";
        log.info("Round with id \"" + id + "\" was successfully created by user \"" + username + "\".");
    }

    @AfterReturning(value = "execution(* com.example.bogroundms.service.RoundService.getRoundsByIds(..))", returning = "roundDTOS")
    public void logWorkoutGet(JoinPoint joinPoint, List<RoundDTO> roundDTOS) {
        Optional<UserDetailsDTO> currentUser = CustomUtils.getCurrentUser();
        String username = currentUser.isPresent() ? currentUser.get().getUsername() : "Anonymous";
        String idList = roundDTOS.stream().map(RoundDTO::getId).collect(Collectors.toList()).toString();
        log.info("User with username \"" + username + "\" got list of rounds with ids:  \"" + idList + "\".");
    }
}
