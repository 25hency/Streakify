package com.streak.Streakify.controller;

import com.streak.Streakify.DTO.StreakResponseDTO;
import com.streak.Streakify.service.StreakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class StreakController {

    private final StreakService service;

    @GetMapping("/{habitId}/streak")
    public ResponseEntity<StreakResponseDTO> getStreak(@PathVariable Long habitId) {
        return ResponseEntity.ok(service.calculateStreak(habitId));
    }
}