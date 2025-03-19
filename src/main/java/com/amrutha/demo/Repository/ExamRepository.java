package com.amrutha.demo.Repository;

import com.amrutha.demo.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ExamRepository extends MongoRepository<Exam, String> {
    List<Exam> findByStudentId(String studentId);
    List<Exam> findByStudentIdAndTerm(String studentId, int term);
    List<Exam> findByStudentIdAndSubjectAndTerm(String studentId, String subject, int term);
}
