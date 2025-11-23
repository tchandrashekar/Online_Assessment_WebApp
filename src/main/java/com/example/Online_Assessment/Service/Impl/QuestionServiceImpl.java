
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.DTO.QuestionDTO;
import com.example.Online_Assessment.Entity.Category;
import com.example.Online_Assessment.Entity.Option;
import com.example.Online_Assessment.Entity.Question;
import com.example.Online_Assessment.Mapper.QuestionMapper;
import com.example.Online_Assessment.Repository.CategoryRepository;
import com.example.Online_Assessment.Repository.OptionRepository;
import com.example.Online_Assessment.Repository.QuestionRepository;
import com.example.Online_Assessment.Service.QuestionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{
    
    private final QuestionRepository questionRepo;
    private final OptionRepository optionRepo;
    private final CategoryRepository categoryRepo;
    private final QuestionMapper questionMapper;
    
    public QuestionServiceImpl(QuestionRepository questionRepo,OptionRepository optionRepo,CategoryRepository categoryRepo,QuestionMapper questionMapper){
        this.questionRepo=questionRepo;
        this.optionRepo=optionRepo;
        this.categoryRepo=categoryRepo;
        this.questionMapper=questionMapper;
    }
    
    @Override
    public QuestionDTO addQuestion(QuestionDTO dto){
        
        Category category=categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found"));
        
        Question question=questionMapper.toEntity(dto,category);
        
        Question savedQuestion=questionRepo.save(question);
        
        List<Option> options=dto.getOptions()
                .stream()
                .map(o ->{
                    Option opt=new Option();
                    opt.setOptionLabel(o.getOptionLabel());
                    opt.setOptionValue(o.getOptionValue());
                    opt.setQuestion(savedQuestion);
                    return opt;
                
                })
                .collect(Collectors.toList());
        optionRepo.saveAll(options);
        savedQuestion.setOptions(options);
        return questionMapper.toDTO(savedQuestion);
        
    }
     @Override
    public QuestionDTO getQuestion(Long id) {
        return questionRepo.findById(id)
                .map(questionMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepo.findAll()
                .stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    
}
