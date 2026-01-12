package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private ProgramState currentState;

    // Σύνδεση 1-προς-Πολλά: Ένα πρόγραμμα έχει πολλές προβολές
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Screening> screenings = new ArrayList<>();

    // Constructors
    public Program() {
        this.createdAt = LocalDateTime.now();
        this.currentState = ProgramState.CREATED; // Ξεκινάει πάντα ως CREATED
    }

    // Getters και Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public ProgramState getCurrentState() { return currentState; }
    public void setCurrentState(ProgramState currentState) { this.currentState = currentState; }
    public List<Screening> getScreenings() { return screenings; }
    public void setScreenings(List<Screening> screenings) { this.screenings = screenings; }
}