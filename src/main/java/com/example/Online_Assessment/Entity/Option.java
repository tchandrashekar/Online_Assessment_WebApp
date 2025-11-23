
package com.example.Online_Assessment.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question_option") 
public class Option {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String optionLabel;
    private String optionValue;
    
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
    
}
