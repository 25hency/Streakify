package com.streak.Streakify.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitRequestDTO {

    @NotBlank
    private String name;

    @Min(1)
    @Max(7)
    @NotNull
    private Integer targetDaysPerWeek;

    @NotNull
    private Long userId;
}
