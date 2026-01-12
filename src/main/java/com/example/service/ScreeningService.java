package com.example.service;

import com.example.model.Program;
import com.example.model.ProgramState;
import com.example.model.Screening;
import com.example.repository.ProgramRepository;
import com.example.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreeningService {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ProgramRepository programRepository;

    public Screening addScreening(Long programId, Screening screening) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Το πρόγραμμα δεν βρέθηκε"));

        // ΚΑΝΟΝΑΣ: Υποβολή μόνο αν το state είναι SUBMISSION
        if (program.getCurrentState() != ProgramState.SUBMISSION) {
            throw new RuntimeException("Δεν επιτρέπονται υποβολές σε αυτή τη φάση!");
        }

        screening.setProgram(program);
        return screeningRepository.save(screening);
    }

    public Screening makeDecision(Long screeningId, boolean accept, String reason) {
        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new RuntimeException("Η προβολή δεν βρέθηκε"));

        if (screening.getProgram().getCurrentState() != ProgramState.DECISION) {
            throw new RuntimeException("Η απόφαση επιτρέπεται μόνο στη φάση DECISION!");
        }

        if (!screening.isFinalized()) {
            screening.setRejectionReason("Απορρίφθηκε αυτόματα: Μη οριστικοποιημένη υποβολή");
            // Εδώ θα μπορούσαμε να βάλουμε και logic για αυτόματη απόρριψη
        } else if (accept) {
            screening.setRejectionReason(null); // Έγινε δεκτή
        } else {
            screening.setRejectionReason(reason); // Απορρίφθηκε με αιτιολογία
        }

        return screeningRepository.save(screening);
    }
}