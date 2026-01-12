package com.example.repository;

import com.example.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    // Μας επιτρέπει να βρίσκουμε όλες τις προβολές ενός συγκεκριμένου προγράμματος
    List<Screening> findByProgramId(Long programId);
}