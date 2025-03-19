package com.amrutha.demo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private List<Term> terms;  // Store multiple terms

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Term> getTerms() { return terms; }
    public void setTerms(List<Term> terms) { this.terms = terms; }
}
