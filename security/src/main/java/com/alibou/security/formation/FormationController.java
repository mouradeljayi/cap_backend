package com.alibou.security.formation;

import com.alibou.security.club.Club;
import com.alibou.security.club.ClubRepository;
import com.alibou.security.club.ClubService;
import com.alibou.security.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formation")
@CrossOrigin
public class FormationController {
    FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Formation>> getAllFormation() {
        return ResponseEntity.ok(formationService.findAllFormation());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Formation> createFormation(@RequestBody Formation formation) {
        return ResponseEntity.ok(formationService.saveFormation(formation));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Formation> updateFormation(@PathVariable Integer id, @RequestBody Formation formation) {
        return ResponseEntity.ok(formationService.updateFormation(formation, id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Formation> findFormation(@PathVariable Integer id) {
        return ResponseEntity.ok(formationService.findFormation(id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteFormation(@PathVariable Integer id) {
        formationService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }
}
