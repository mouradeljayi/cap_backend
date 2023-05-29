package com.alibou.security.club;

import com.alibou.security.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/club")
@CrossOrigin
public class ClubController {

    ClubService clubService;
    UserRepository userRepository;
    ClubRepository clubRepository;

    public ClubController(ClubService clubService, UserRepository userRepository, ClubRepository clubRepository) {
        this.clubService = clubService;
        this.userRepository = userRepository;
        this.clubRepository = clubRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Club>> getAllClub() {
        return ResponseEntity.ok(clubService.findAllClub());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return ResponseEntity.ok(clubService.saveClub(club));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Club> updateClub(@PathVariable Integer id, @RequestBody Club club) {
        return ResponseEntity.ok(clubService.updatClub(club, id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Club> findClub(@PathVariable Integer id) {
        return ResponseEntity.ok(clubService.findClub(id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteClub(@PathVariable Integer id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/clubs/{clubId}")
    public ResponseEntity<String> addUserToClub(@PathVariable Integer userId, @PathVariable Integer clubId) {
        clubService.addUserToClub(userId, clubId);
        return ResponseEntity.ok("User added to club successfully.");
    }



}
