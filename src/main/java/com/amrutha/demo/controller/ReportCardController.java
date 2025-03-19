package com.amrutha.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amrutha.demo.Service.ReportCardService;
import com.amrutha.demo.Repository.ExamRepository;
import com.amrutha.demo.Repository.StudentRepository;
import com.amrutha.demo.model.Exam;
import com.amrutha.demo.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class ReportCardController {

    @Autowired
    private ReportCardService service;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/{studentId}/exams")
    public ResponseEntity<Exam> addExam(@PathVariable String studentId, @RequestBody Exam exam) {
        exam.setStudentId(studentId);
        exam.setScore(exam.calculateScore());
        Exam savedExam = examRepository.save(exam);
        return ResponseEntity.ok(savedExam);
    }

@GetMapping("/{studentId}/report-card")
public ResponseEntity<Map<String, Map<String, Double>>> getReportCard(@PathVariable String studentId) {
    return ResponseEntity.ok(service.generateReportCard(studentId));
}


    @PutMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable String studentId, 
                                           @PathVariable String examId, 
                                           @RequestBody Exam updatedExam) {
        Optional<Exam> examOptional = examRepository.findById(examId);
        if (examOptional.isPresent()) {
            Exam exam = examOptional.get();
            exam.setExam1(updatedExam.getExam1());
        exam.setExam2(updatedExam.getExam2());
        exam.setExam3(updatedExam.getExam3());
            exam.setScore(updatedExam.calculateScore());
            Exam savedExam = examRepository.save(exam);
            return ResponseEntity.ok(savedExam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable String studentId, @PathVariable String examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{studentId}/exams")
    public ResponseEntity<List<Exam>> getAllExams(@PathVariable String studentId) {
        List<Exam> exams = examRepository.findByStudentId(studentId);
        return ResponseEntity.ok(exams);
    }
}
