package com.amrutha.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Document(collection = "exams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    private String id;
    private String studentId;
    private String term;
    private String subject;  // ✅ Added subject field
    private double exam1;
    private double exam2;
    private double exam3;
    private double score; 
    private String examId;
    
    // Constructor that calculates score
    public Exam(String studentId, String term, String subject, double exam1, double exam2, double exam3, String examId) {
        this.studentId = studentId;
        this.term = term;
        this.subject = subject;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
        this.examId = examId;
        this.score = calculateScore();  // Ensure score is calculated on object creation
    }

    // Method to calculate score
    public double calculateScore() {
        return (0.1 * exam1) + (0.1 * exam2) + (0.8 * exam3);
    }

    // Update the score whenever exam values change
    public void setExam1(double exam1) {
        this.exam1 = exam1;
        this.score = calculateScore();
    }

    public void setExam2(double exam2) {
        this.exam2 = exam2;
        this.score = calculateScore();
    }

    public void setExam3(double exam3) {
        this.exam3 = exam3;
        this.score = calculateScore();
    }
}
