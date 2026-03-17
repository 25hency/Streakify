package com.streak.Streakify.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogResponseDTO {
    private Long id;
    private Long habitId;
    private LocalDate logDate;
    private Boolean completed;
}
