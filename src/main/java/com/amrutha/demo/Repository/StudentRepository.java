package com.amrutha.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.amrutha.demo.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {}
