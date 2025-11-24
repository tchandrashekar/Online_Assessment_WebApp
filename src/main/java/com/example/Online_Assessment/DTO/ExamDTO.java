
package com.example.Online_Assessment.DTO;

import java.util.List;
import lombok.Data;

@Data
public class ExamDTO {
    
    private Long id;
    private String title;
    private Integer duration;
    private Integer totalMarks;
    
    private List<Long> questionIds;
}
