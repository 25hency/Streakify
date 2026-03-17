package com.streak.Streakify.repository;

import com.streak.Streakify.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

    long countByHabitUserIdAndLogDateAndCompletedTrue(Long userId, LocalDate date);
    long countByHabitUserId(Long userId);
    long countByHabitUserIdAndCompletedTrue(Long userId);
    Optional<HabitLog> findByHabitIdAndLogDate(Long habitId, LocalDate logDate);
    List<HabitLog> findByHabitIdOrderByLogDateAsc(Long habitId);
}
