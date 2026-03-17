package com.streak.Streakify.service;

import com.streak.Streakify.DTO.HabitLogRequestDTO;
import com.streak.Streakify.DTO.HabitLogResponseDTO;
import com.streak.Streakify.entity.HabitLog;
import com.streak.Streakify.entity.Habit;
import com.streak.Streakify.exception.BadRequestException;
import com.streak.Streakify.exception.ResourceNotFoundException;
import com.streak.Streakify.repository.HabitLogRepository;
import com.streak.Streakify.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitLogService {

    private final HabitLogRepository repo;
    private final HabitRepository habitRepository;

    // Create Log
    public HabitLogResponseDTO createLog(Long habitId, HabitLogRequestDTO request) {

        LocalDate logDate = request.getLogDate();

        repo.findByHabitIdAndLogDate(habitId, logDate)
                .ifPresent(log -> {
                    throw new BadRequestException("Log already exists for this date");
                });

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        HabitLog log = HabitLog.builder()
                .habit(habit)
                .logDate(logDate)
                .completed(request.getCompleted())
                .build();

        return mapToResponse(repo.save(log));
    }

    // Update Log
    public HabitLogResponseDTO updateLog(Long habitId, LocalDate date, HabitLogRequestDTO request) {

        if (date.isAfter(LocalDate.now())) {
            throw new BadRequestException("Date path parameter cannot be in the future");
        }

        HabitLog existing = repo.findByHabitIdAndLogDate(habitId, date)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        existing.setCompleted(request.getCompleted());

        return mapToResponse(repo.save(existing));
    }

    // Get Logs
    public List<HabitLogResponseDTO> getLogs(Long habitId) {

        return repo.findByHabitIdOrderByLogDateAsc(habitId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Convert Entity → DTO
    private HabitLogResponseDTO mapToResponse(HabitLog log) {
        return HabitLogResponseDTO.builder()
                .id(log.getId())
                .habitId(log.getHabit().getId())
                .logDate(log.getLogDate())
                .completed(log.getCompleted())
                .build();
    }
}