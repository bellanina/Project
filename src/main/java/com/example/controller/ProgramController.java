package com.example.controller;


import com.example.model.Program;
import com.example.model.ProgramState;
import com.example.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    // 1. Δημιουργία Προγράμματος (POST http://localhost:8080/api/programs)
    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        return ResponseEntity.ok(programService.createProgram(program));
    }

    // 2. Προβολή όλων των προγραμμάτων (GET http://localhost:8080/api/programs)
    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    // 3. Αλλαγή Κατάστασης (PUT http://localhost:8080/api/programs/{id}/state)
    // Παράδειγμα: /api/programs/1/state?newState=SUBMISSION
    @PutMapping("/{id}/state")
    public ResponseEntity<Program> changeState(
            @PathVariable Long id,
            @RequestParam ProgramState newState) {
        return ResponseEntity.ok(programService.nextState(id, newState));
    }

    // Προβολή μόνο των ανακοινωμένων προγραμμάτων (Για Visitors)
    // GET http://localhost:8080/api/programs/announced
    @GetMapping("/announced")
    public ResponseEntity<List<Program>> getAnnounced() {
        return ResponseEntity.ok(programService.getAnnouncedPrograms());
    }
}