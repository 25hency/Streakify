package com.streak.Streakify.repository;

import com.streak.Streakify.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    long countByUserId(Long userId);
    List<Habit> findByUserId(Long userId);
}
