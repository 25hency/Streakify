package com.streak.Streakify.service;

import com.streak.Streakify.DTO.HabitRequestDTO;
import com.streak.Streakify.DTO.HabitResponseDTO;
import com.streak.Streakify.entity.Habit;
import com.streak.Streakify.entity.User;
import com.streak.Streakify.exception.ResourceNotFoundException;
import com.streak.Streakify.repository.HabitRepository;
import com.streak.Streakify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;


    public HabitResponseDTO createHabit(HabitRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Habit habit = Habit.builder()
                .name(request.getName())
                .targetDaysPerWeek(request.getTargetDaysPerWeek())
                .user(user)
                .build();

        Habit savedHabit = habitRepository.save(habit);
        return mapToResponse(savedHabit);
    }

    public List<HabitResponseDTO> getUserHabits(Long userId) {
        return habitRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteHabit(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Habit not found");
        }
        habitRepository.deleteById(id);
    }

    private HabitResponseDTO mapToResponse(Habit habit) {
        return HabitResponseDTO.builder()
                .id(habit.getId())
                .name(habit.getName())
                .targetDaysPerWeek(habit.getTargetDaysPerWeek())
                .userId(habit.getUser().getId())
                .created_at(habit.getCreatedAt())
                .build();
    }
}
