package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieTitle;
    private String genre;
    private int duration; // σε λεπτά
    private String room;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer grade; // Βαθμολογία από το Staff
    private String reviewComments;
    private String rejectionReason;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    private boolean finalized = false;

    // Constructors
    public Screening() {}

    // Getters και Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public Program getProgram() { return program; }
    public void setProgram(Program program) { this.program = program; }
    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }
    public String getReviewComments() { return reviewComments; }
    public void setReviewComments(String reviewComments) { this.reviewComments = reviewComments; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public boolean isFinalized() { return finalized; }
    public void setFinalized(boolean finalized) { this.finalized = finalized; }
}