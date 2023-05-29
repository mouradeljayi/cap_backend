package com.alibou.security.auth;

import com.alibou.security.user.User;
import com.alibou.security.user.UserDto;
import com.alibou.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/me")
  public UserDto getCurrentUser(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setFirstname(user.getFirstname());
    userDto.setLastname(user.getLastname());
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    userDto.setRole(user.getRole());
    userDto.setClubs(user.getClubs());

    return userDto;
  }


  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> allUsers() {
    return ResponseEntity.ok(service.findAllUsers());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/createUser")
  public ResponseEntity<User> createUser(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.createUser(request));
  }

  @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/changePassword/{userId}")
  public ResponseEntity<User> changePassword(@PathVariable Integer userId, @RequestBody String newPassword) {
    return ResponseEntity.ok(service.changePassword(userId, newPassword));
  }
}
