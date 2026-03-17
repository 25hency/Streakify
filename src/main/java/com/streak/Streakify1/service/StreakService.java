package com.streak.Streakify.service;

import com.streak.Streakify.DTO.StreakResponseDTO;
import com.streak.Streakify.entity.HabitLog;
import com.streak.Streakify.repository.HabitLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreakService {
    private final HabitLogRepository repo;

    public StreakResponseDTO calculateStreak(Long habitId) {

        List<HabitLog> logs = repo.findByHabitIdOrderByLogDateAsc(habitId);

        int currentStreak = 0;
        int longestStreak = 0;
        int temp = 0;

        LocalDate prevDate = null;

        for (HabitLog log : logs) {

            if (!Boolean.TRUE.equals(log.getCompleted())) {
                temp = 0;
                prevDate = log.getLogDate();
                continue;
            }

            if (prevDate == null || log.getLogDate().equals(prevDate.plusDays(1))) {
                temp++;
            } else {
                temp = 1;
            }

            longestStreak = Math.max(longestStreak, temp);
            prevDate = log.getLogDate();
        }

        // Calculate current streak (from last date backwards)
        LocalDate today = LocalDate.now();
        currentStreak = 0;

        for (int i = logs.size() - 1; i >= 0; i--) {
            HabitLog log = logs.get(i);

            if (!Boolean.TRUE.equals(log.getCompleted())) break;

            if (log.getLogDate().equals(today.minusDays(currentStreak))) {
                currentStreak++;
            } else {
                break;
            }
        }

        return new StreakResponseDTO(currentStreak, longestStreak);
    }
}
