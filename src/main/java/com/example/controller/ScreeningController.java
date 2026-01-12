package com.example.controller;

import com.example.model.Screening;
import com.example.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    // Υποβολή ταινίας σε συγκεκριμένο πρόγραμμα
    // POST http://localhost:8080/api/screenings?programId=1
    @PostMapping
    public ResponseEntity<Screening> submitScreening(
            @RequestParam Long programId,
            @RequestBody Screening screening) {
        return ResponseEntity.ok(screeningService.addScreening(programId, screening));
    }
}