package com.streak.Streakify.controller;

import com.streak.Streakify.DTO.DashboardResponseDTO;
import com.streak.Streakify.DTO.UserRequestDTO;
import com.streak.Streakify.DTO.UserResponseDTO;
import com.streak.Streakify.service.DashboardService;
import com.streak.Streakify.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DashboardService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userReqDTO){
        return ResponseEntity.ok(userService.createUser(userReqDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User Successfully Deleted");

    }

    @GetMapping("/{userId}/dashboard")
    public ResponseEntity<DashboardResponseDTO> getDashboard(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getDashboard(userId));
    }
}
