
package com.example.OnlineAssessment_Exam.Model;


import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Assessment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int duration;
    
    @OneToMany(mappedBy="assessment",cascade=CascadeType.ALL)
    private List<Question> questions;
    
}
