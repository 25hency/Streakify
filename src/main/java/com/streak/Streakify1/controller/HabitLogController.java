package com.streak.Streakify.controller;

import com.streak.Streakify.DTO.HabitLogRequestDTO;
import com.streak.Streakify.DTO.HabitLogResponseDTO;
import com.streak.Streakify.service.HabitLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/habits/{habitId}/logs")
public class HabitLogController {

    private final HabitLogService habitLogService;

    // Create Log
    @PostMapping
    public ResponseEntity<HabitLogResponseDTO> createLog(
            @PathVariable Long habitId,
            @Valid @RequestBody HabitLogRequestDTO request) {

        return ResponseEntity.ok(
                habitLogService.createLog(habitId, request)
        );
    }

    // Update Log
    @PutMapping("/{date}")
    public ResponseEntity<HabitLogResponseDTO> updateLog(
            @PathVariable Long habitId,
            @PathVariable LocalDate date,
            @Valid @RequestBody HabitLogRequestDTO request) {

        return ResponseEntity.ok(
                habitLogService.updateLog(habitId, date, request)
        );
    }

    // Get Logs
    @GetMapping
    public ResponseEntity<List<HabitLogResponseDTO>> getLogs(
            @PathVariable Long habitId) {

        List<HabitLogResponseDTO> logs =
                habitLogService.getLogs(habitId);

        return ResponseEntity.ok(logs);
    }
}