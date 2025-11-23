
package com.example.Online_Assessment.Mapper;

import com.example.Online_Assessment.DTO.QuestionDTO;
import com.example.Online_Assessment.Entity.Category;
import com.example.Online_Assessment.Entity.Question;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    
    private final OptionMapper optionMapper;
    
    public QuestionMapper(OptionMapper optionMapper){
        this.optionMapper=optionMapper;        
    }
    
    public QuestionDTO toDTO(Question question){
        QuestionDTO dto=new QuestionDTO();
        dto.setId(question.getId());
        dto.setTitle(question.getTitle());
        dto.setCategoryId(question.getCategory().getId());
        dto.setCorrectAnswer(question.getCorrectAnswer());
        
        dto.setOptions(
                question.getOptions()
                .stream()
                .map(optionMapper::toDTO)
                .collect(Collectors.toList())
        );
        return dto;
    }
    
    public Question toEntity(QuestionDTO dto,Category category){
        Question question=new Question();
        question.setId(dto.getId());
        question.setTitle(dto.getTitle());
        question.setCategory(category);
        question.setCorrectAnswer(dto.getCorrectAnswer());
        return question;
    }
    
}
