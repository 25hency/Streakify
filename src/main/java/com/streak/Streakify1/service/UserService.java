package com.streak.Streakify.service;

import com.streak.Streakify.DTO.UserRequestDTO;
import com.streak.Streakify.DTO.UserResponseDTO;
import com.streak.Streakify.entity.User;
import com.streak.Streakify.exception.ResourceNotFoundException;
import com.streak.Streakify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userReqDTO) {

        User user = User.builder()
                .name(userReqDTO.getName())
                .email(userReqDTO.getEmail())
                .build();

        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
