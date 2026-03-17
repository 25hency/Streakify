package com.streak.Streakify.controller;

import com.streak.Streakify.DTO.HabitRequestDTO;
import com.streak.Streakify.DTO.HabitResponseDTO;
import com.streak.Streakify.service.HabitService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Data
@RestController
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    // POST /habits
    @PostMapping("/habits")
    public ResponseEntity<HabitResponseDTO> createHabit(
            @Valid @RequestBody HabitRequestDTO habitRequest) {

        HabitResponseDTO saved = habitService.createHabit(habitRequest);
        return ResponseEntity.ok(saved);
    }

    // GET /users/{userId}/habits
    @GetMapping("/users/{userId}/habits")
    public ResponseEntity<List<HabitResponseDTO>> getUserHabits(@PathVariable Long userId) {
        List<HabitResponseDTO> habits = habitService.getUserHabits(userId);
        return ResponseEntity.ok(habits);
    }

    // DELETE /habits/{id}
    @DeleteMapping("/habits/{id}")
    public ResponseEntity<?> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.ok("Habit deleted successfully");
    }
}
