package com.example.bogroundms.feign;


import com.example.bogroundms.feign.feignDtos.UserDTO;
import com.example.bogroundms.feign.feignDtos.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

    @GetMapping("/api/user")
    UserDTO getUserByLogin(@RequestParam(name = "login") String login);

    @GetMapping("/api/user/details")
    UserDetailsDTO getUserDetailsByLogin(@RequestParam(name = "login") String login);
}
