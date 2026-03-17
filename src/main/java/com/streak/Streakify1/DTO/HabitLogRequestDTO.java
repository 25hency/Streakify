package com.streak.Streakify.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitLogRequestDTO {

    @NotNull(message = "logDate is required")
    @PastOrPresent(message = "logDate cannot be in the future")
    private LocalDate logDate;
    @NotNull(message = "completed is required")
    private Boolean completed;
}
