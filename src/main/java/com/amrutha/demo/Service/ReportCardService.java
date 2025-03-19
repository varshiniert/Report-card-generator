package com.amrutha.demo.Service;

import com.amrutha.demo.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amrutha.demo.Repository.ExamRepository;
import java.util.*;

@Service
public class ReportCardService {

    @Autowired
    private ExamRepository examRepository;

    public Map<String, Map<String, Double>> generateReportCard(String studentId) {
        List<Exam> exams = examRepository.findByStudentId(studentId);
        Map<String, Map<String, Double>> subjectReport = new HashMap<>();

        // Group exams by subject and term
        Map<String, Map<String, List<Exam>>> groupedExams = new HashMap<>();
        for (Exam exam : exams) {
            String subject = exam.getSubject();
            String term = getTermFromExam(exam); // Dynamically assign term
            groupedExams.putIfAbsent(subject, new HashMap<>());
            groupedExams.get(subject).putIfAbsent(term, new ArrayList<>());
            groupedExams.get(subject).get(term).add(exam);
        }

        // Process subjects and terms
        for (String subject : groupedExams.keySet()) {
            Map<String, Double> termScores = new HashMap<>();
            for (String term : groupedExams.get(subject).keySet()) {
                List<Exam> termExams = groupedExams.get(subject).get(term);

                // Calculate weighted term score
                double termScore = calculateWeightedTermScore(termExams);
                termScores.put(term, termScore);
            }

            // Compute final subject score (average across terms)
            double finalScore = termScores.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            termScores.put("Final " + subject + " Score", finalScore);

            subjectReport.put(subject, termScores);
        }

        return subjectReport;
    }

    private String getTermFromExam(Exam exam) {
        int examNumber = Integer.parseInt(exam.getExamId()); // Ensure Exam has a unique ID
        return (examNumber <= 3) ? "Term 1" : "Term 2";
    }

    private double calculateWeightedTermScore(List<Exam> exams) {
        if (exams.size() < 3) return 0.0; // Ensure at least three exams exist
    
        return (0.1 * exams.get(0).getScore()) + 
               (0.1 * exams.get(1).getScore()) + 
               (0.8 * exams.get(2).getScore());
    }
    
    
}
