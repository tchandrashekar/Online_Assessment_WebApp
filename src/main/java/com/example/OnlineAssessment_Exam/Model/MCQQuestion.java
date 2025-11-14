
package com.example.OnlineAssessment_Exam.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@DiscriminatorValue("MCQ")
public class MCQQuestion extends Question{
    
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    
    @Override
    public String getType(){
        return "MCQ";
    }
    
}
