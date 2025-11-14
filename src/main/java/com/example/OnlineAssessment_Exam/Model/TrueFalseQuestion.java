
package com.example.OnlineAssessment_Exam.Model;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@DiscriminatorValue("TRUE_FALSE")
public class TrueFalseQuestion extends Question{
    
    private Boolean correctAnswer;
    
    @Override
    public String getType(){
        return "TRUE_FALSE";
    }
    
}
