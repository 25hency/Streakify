package com.streak.Streakify.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponseDTO {
    private Long totalHabits;
    private Long activeHabits;
    private Long completedToday;
    private List<DashboardStreakDTO> currentStreaks;
    private String consistencyScore;
}
