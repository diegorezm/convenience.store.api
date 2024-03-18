package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserResponseDTO;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserNotFoundException;
import com.conveniencestore.conveniencestore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService implements ServiceInterface<UserResponseDTO, UserDTO> {
    private final UserRepository userRepository;

    public UserResponseDTO insert(UserDTO data) {
        if (userRepository.findUserByEmail(data.email()).isPresent()) return null;
        String password = new BCryptPasswordEncoder().encode(data.password());
        data = new UserDTO(data.username(), data.email(), password, data.role());
        User user = new User(data);
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public List<UserResponseDTO> getAll(String orderby, String order) {
        Sort.Direction direction;
        switch (order) {
            case "asc" -> {
                direction = Sort.Direction.ASC;
            }
            case "desc" -> {
                direction = Sort.Direction.DESC;
            }
            default -> {
                direction = Sort.DEFAULT_DIRECTION;
            }
        }
        return this.userRepository.findAllFilteredResponse(Sort.by(direction, orderby));
    }

    public UserResponseDTO getById(int id) {
        User user= this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new UserResponseDTO(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }

    public UserResponseDTO delete(int id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
        return new UserResponseDTO(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }

    public UserResponseDTO update(int id, UserDTO data) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setRole(data.role());
        user.setEmail(data.email());
        user.setEmail(data.email());
        this.userRepository.save(user);
        return new UserResponseDTO(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }
}
