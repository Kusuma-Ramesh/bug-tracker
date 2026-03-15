package com.devops.bugtracker.controller;

import com.devops.bugtracker.model.Bug;
import com.devops.bugtracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
@CrossOrigin(origins = "*")
public class BugController {

    @Autowired
    private BugService bugService;

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bug> getBugById(@PathVariable Long id) {
        return bugService.getBugById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.createBug(bug);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bug> updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        try {
            return ResponseEntity.ok(bugService.updateBug(id, bug));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<Bug> getBugsByStatus(@PathVariable Bug.Status status) {
        return bugService.getBugsByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Bug> getBugsByPriority(@PathVariable Bug.Priority priority) {
        return bugService.getBugsByPriority(priority);
    }
}