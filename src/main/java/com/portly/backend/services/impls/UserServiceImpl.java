package com.portly.backend.services.impls;

import com.portly.backend.dto.input.EditNameDto;
import com.portly.backend.dto.input.EditPasswordDto;
import com.portly.backend.dto.input.SignUpDto;
import com.portly.backend.dto.UserDto;
import com.portly.backend.entities.User;
import com.portly.backend.entities.enums.Role;
import com.portly.backend.exceptions.ResourceNotFoundException;
import com.portly.backend.repositories.UserRepository;
import com.portly.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () ->  new ResourceNotFoundException("User is not found with UserId " + userId)
        );
    }

    @Override
    public User createUser(SignUpDto signupDto) {
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email "+ signupDto.getEmail() +" is already present.");
        }
        User userToSave = modelMapper.map(signupDto, User.class);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userToSave.setRoles(List.of(Role.USER));
        return userRepository.save(userToSave);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () ->  new ResourceNotFoundException("User is not found with emailId " + email)
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserByUri(String uri) {
        return userRepository.findByUri(uri).orElse(null);
    }

    @Override
    public UserDto getMyProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    @Transactional
    public String editPassword(EditPasswordDto editPasswordDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getPassword().equals(passwordEncoder.encode(editPasswordDto.getOldPassword()))){
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(editPasswordDto.getNewPassword()));
        updateUser(user);
        return "Password updated successfully...";
    }

    @Override
    @Transactional
    public String editName(EditNameDto editNameDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setName(editNameDto.getName());
        updateUser(user);
        return "Name updated successfully...";
    }


    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User with email "+ username +" is not found."));

    }
}
