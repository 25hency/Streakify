package com.streak.Streakify.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "habit_logs", uniqueConstraints = @UniqueConstraint(columnNames = {"habit_id", "log_date"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate logDate;

    @Column(nullable = false)
    private Boolean completed;
}
