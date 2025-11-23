
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.QuestionDTO;
import com.example.Online_Assessment.Service.QuestionService;
import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    
    private final QuestionService service;
    public QuestionController(QuestionService service){
        this.service=service;
    }
    
    @PostMapping
    public QuestionDTO addQuestion(@RequestBody QuestionDTO dto){
        return service.addQuestion(dto);
    }
    
    @GetMapping("/{id}")
    public QuestionDTO getQuestion(@PathVariable Long id){
        return service.getQuestion(id);
    }
    
    @GetMapping
    public List<QuestionDTO> getALLQuestions(){
        return service.getAllQuestions();
    }
    
}
