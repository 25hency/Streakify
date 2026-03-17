package com.streak.Streakify.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardStreakDTO {
    private String habitName;
    private Integer currentStreak;
    private Integer longestStreak;
}
