package com.streak.Streakify.service;

import com.streak.Streakify.DTO.DashboardResponseDTO;
import com.streak.Streakify.DTO.DashboardStreakDTO;
import com.streak.Streakify.entity.HabitLog;
import com.streak.Streakify.entity.Habit;
import com.streak.Streakify.repository.HabitLogRepository;
import com.streak.Streakify.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final HabitRepository repo;
    private final HabitLogRepository habitLogRepository;

    public DashboardResponseDTO getDashboard(Long userId) {

        LocalDate today = LocalDate.now();

        // 1. Total Habits
        long totalHabits = repo.countByUserId(userId);

        // 2. Active Habits (simple version = totalHabits)
        long activeHabits = totalHabits;

        // 3. Completed Today
        long completedToday =
                habitLogRepository.countByHabitUserIdAndLogDateAndCompletedTrue(userId, today);

        // 4. Streaks
        List<Habit> habits = repo.findByUserId(userId);

        List<DashboardStreakDTO> streakList = habits.stream()
                .map(habit -> calculateStreak(habit))
                .toList();

        // 5. Consistency Score
        String consistencyScore = calculateConsistency(userId);

        return new DashboardResponseDTO(
                totalHabits,
                activeHabits,
                completedToday,
                streakList,
                consistencyScore
        );
    }

    private DashboardStreakDTO calculateStreak(Habit habit) {

        List<HabitLog> logs =
                habitLogRepository.findByHabitIdOrderByLogDateAsc(habit.getId());

        int currentStreak = 0;
        int longestStreak = 0;
        int tempStreak = 0;

        LocalDate prevDate = null;

        for (HabitLog log : logs) {

            if (!Boolean.TRUE.equals(log.getCompleted())) continue;

            if (prevDate == null ||
                    log.getLogDate().equals(prevDate.plusDays(1))) {
                tempStreak++;
            } else {
                tempStreak = 1;
            }

            longestStreak = Math.max(longestStreak, tempStreak);
            prevDate = log.getLogDate();
        }

        currentStreak = tempStreak;

        // ensure streak is valid up to today/yesterday
        if (prevDate == null ||
                !(prevDate.equals(LocalDate.now()) ||
                        prevDate.equals(LocalDate.now().minusDays(1)))) {
            currentStreak = 0;
        }

        return new DashboardStreakDTO(
                habit.getName(),
                currentStreak,
                longestStreak
        );
    }

    private String calculateConsistency(Long userId) {
        long totalDaysTracked = habitLogRepository.countByHabitUserId(userId);
        if (totalDaysTracked == 0) return "0.0%";

        long daysCompleted = habitLogRepository.countByHabitUserIdAndCompletedTrue(userId);
        double percentage = (daysCompleted * 100.0) / totalDaysTracked;

        return String.format(Locale.US, "%.1f%%", percentage);
    }
}

