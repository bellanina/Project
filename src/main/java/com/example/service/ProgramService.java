package com.example.service;

import com.example.model.Program;
import com.example.model.ProgramState;
import com.example.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    // Δημιουργία νέου προγράμματος (Ξεκινάει πάντα ως CREATED)
    public Program createProgram(Program program) {
        if (programRepository.findByName(program.getName()).isPresent()) {
            throw new RuntimeException("Το όνομα του προγράμματος υπάρχει ήδη!");
        }
        program.setCurrentState(ProgramState.CREATED);
        return programRepository.save(program);
    }

    // Αλλαγή κατάστασης με βάση τη ροή (Business Logic)
    public Program nextState(Long id, ProgramState newState) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Το πρόγραμμα δεν βρέθηκε"));

        ProgramState currentState = program.getCurrentState();

        // Έλεγχος αν η μετάβαση είναι έγκυρη (Αυστηρά γραμμική ροή)
        if (!isValidTransition(currentState, newState)) {
            throw new RuntimeException("Μη έγκυρη μετάβαση από " + currentState + " σε " + newState);
        }

        program.setCurrentState(newState);
        return programRepository.save(program);
    }

    // Βοηθητική μέθοδος για τον έλεγχο της σειράς των καταστάσεων
    private boolean isValidTransition(ProgramState current, ProgramState next) {
        // Η εκφώνηση απαγορεύει την επιστροφή σε προηγούμενη κατάσταση
        return next.ordinal() == current.ordinal() + 1;
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public List<Program> getAnnouncedPrograms() {
        return programRepository.findByCurrentState(ProgramState.ANNOUNCED);
    }
}