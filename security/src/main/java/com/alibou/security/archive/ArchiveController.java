package com.alibou.security.archive;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/archive")
@CrossOrigin
public class ArchiveController {
    ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Archive>> getAllArchive() {
        return ResponseEntity.ok(archiveService.findAllArchive());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Archive> createArchive(@RequestBody Archive archive) {
        return ResponseEntity.ok(archiveService.saveArchive(archive));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Archive> updateArchive(@PathVariable Integer id, @RequestBody Archive archive) {
        return ResponseEntity.ok(archiveService.updateArchive(archive, id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Archive> findArchive(@PathVariable Integer id) {
        return ResponseEntity.ok(archiveService.findArchive(id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteArchive(@PathVariable Integer id) {
        archiveService.deleteArchive(id);
        return ResponseEntity.noContent().build();
    }
}
