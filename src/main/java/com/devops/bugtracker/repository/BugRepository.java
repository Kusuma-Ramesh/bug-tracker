package com.devops.bugtracker.repository;

import com.devops.bugtracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    List<Bug> findByStatus(Bug.Status status);

    List<Bug> findByPriority(Bug.Priority priority);

    List<Bug> findByAssignedTo(String assignedTo);
}