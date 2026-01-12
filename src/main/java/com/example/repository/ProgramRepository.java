package com.example.repository;

import com.example.model.Program;
import com.example.model.ProgramState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    // Μας επιτρέπει να βρίσκουμε ένα πρόγραμμα βάσει ονόματος (που ορίσαμε ως unique)
    Optional<Program> findByName(String name);
    List<Program> findByCurrentState(ProgramState state);
}