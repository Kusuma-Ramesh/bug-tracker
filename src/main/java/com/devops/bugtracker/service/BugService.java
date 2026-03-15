package com.devops.bugtracker.service;

import com.devops.bugtracker.model.Bug;
import com.devops.bugtracker.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BugService {

    @Autowired
    private BugRepository bugRepository;

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    public Optional<Bug> getBugById(Long id) {
        return bugRepository.findById(id);
    }

    public Bug createBug(Bug bug) {
        return bugRepository.save(bug);
    }

    public Bug updateBug(Long id, Bug updatedBug) {
        return bugRepository.findById(id).map(bug -> {
            bug.setTitle(updatedBug.getTitle());
            bug.setDescription(updatedBug.getDescription());
            bug.setAssignedTo(updatedBug.getAssignedTo());
            bug.setPriority(updatedBug.getPriority());
            bug.setStatus(updatedBug.getStatus());
            return bugRepository.save(bug);
        }).orElseThrow(() -> new RuntimeException("Bug not found: " + id));
    }

    public void deleteBug(Long id) {
        bugRepository.deleteById(id);
    }

    public List<Bug> getBugsByStatus(Bug.Status status) {
        return bugRepository.findByStatus(status);
    }

    public List<Bug> getBugsByPriority(Bug.Priority priority) {
        return bugRepository.findByPriority(priority);
    }
}