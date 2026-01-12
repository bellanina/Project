package com.example;

import com.example.model.Program;
import com.example.model.ProgramState;
import com.example.service.ProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CinemaApplicationTests {

    @Autowired
    private ProgramService programService;

    @Test
    void testValidStateTransition() {
        // 1. Δημιουργία προγράμματος
        Program program = new Program();
        program.setName("Test Festival");
        Program saved = programService.createProgram(program);

        assertEquals(ProgramState.CREATED, saved.getCurrentState());

        // 2. Μετάβαση στο επόμενο (SUBMISSION) - Πρέπει να πετύχει
        Program updated = programService.nextState(saved.getId(), ProgramState.SUBMISSION);
        assertEquals(ProgramState.SUBMISSION, updated.getCurrentState());
    }

    @Test
    void testInvalidStateTransition() {
        // 1. Δημιουργία προγράμματος
        Program program = new Program();
        program.setName("Error Festival");
        Program saved = programService.createProgram(program);

        // 2. Προσπάθεια για REVIEW (πήδημα σταδίων) - Πρέπει να πετάξει Exception
        assertThrows(RuntimeException.class, () -> {
            programService.nextState(saved.getId(), ProgramState.REVIEW);
        });
    }
}