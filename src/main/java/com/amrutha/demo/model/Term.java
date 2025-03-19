package com.amrutha.demo.model;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public class Term {
    private String termName;
    private List<Double> examScores; 
    
    public double getTermScore() {
        if (examScores == null || examScores.size() < 3) return 0.0;
        return (0.1 * examScores.get(0)) + (0.1 * examScores.get(1)) + (0.8 * examScores.get(2));
    }

    
    public String getTermName() { return termName; }
    public void setTermName(String termName) { this.termName = termName; }

    public List<Double> getExamScores() { return examScores; }
    public void setExamScores(List<Double> examScores) { this.examScores = examScores; }
}
