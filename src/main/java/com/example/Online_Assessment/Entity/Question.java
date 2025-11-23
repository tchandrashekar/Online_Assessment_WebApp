
package com.example.Online_Assessment.Entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    
    @OneToMany(mappedBy ="question" , cascade=CascadeType.ALL)
    private List<Option> options;
    private String correctAnswer;
    
}
