package com.streak.Streakify.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitResponseDTO {
    private Long id;
    private String name;
    private Integer targetDaysPerWeek;
    private Long userId;
    private LocalDateTime created_at;
}

