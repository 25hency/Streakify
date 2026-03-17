package com.streak.Streakify.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreakResponseDTO {
    private Integer currentStreak;
    private Integer longestStreak;
}
