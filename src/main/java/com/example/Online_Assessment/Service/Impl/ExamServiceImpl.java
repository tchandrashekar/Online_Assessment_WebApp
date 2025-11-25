
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.DTO.ExamDTO;
import com.example.Online_Assessment.DTO.ExamStartDTO;
import com.example.Online_Assessment.Entity.Exam;
import com.example.Online_Assessment.Entity.Option;
import com.example.Online_Assessment.Entity.Question;
import com.example.Online_Assessment.Repository.ExamRepository;
import com.example.Online_Assessment.Repository.QuestionRepository;
import com.example.Online_Assessment.Service.ExamService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    
    private final ExamRepository examRepo;
    private final QuestionRepository questionRepo;
    
    @Override
    public Exam createExam(ExamDTO dto){
        Exam exam=new Exam();
        exam.setTitle(dto.getTitle());
        exam.setDuration(dto.getDuration());
        exam.setTotalMarks(dto.getTotalMarks());
        
        List<Question> questionList=new ArrayList<>();
        
        if(dto.getQuestionIds()!=null){
            for(Long id:dto.getQuestionIds()){
                questionRepo.findById(id).ifPresent(questionList::add);
            }
        }
        exam.setQuestions(questionList);
        return examRepo.save(exam);
    }
    
    @Override
    public Exam addQuestionToExam(Long examId,Long questionId){
        
        Exam exam=examRepo.findById(examId).orElseThrow();
        Question q=questionRepo.findById(questionId).orElseThrow();
        exam.getQuestions().add(q);
        
        return examRepo.save(exam);
    }
    
    @Override
    public Exam removeQuestionFromExam(Long examId,Long questionId){
        Exam exam=examRepo.findById(examId).orElseThrow();
        Question q= questionRepo.findById(questionId).orElseThrow();
        exam.getQuestions().remove(q);
        return examRepo.save(exam);
    }
    
    public ExamStartDTO startExam(Long examId, Long userId) {

    Exam exam = examRepo.findById(examId)
            .orElseThrow(() -> new RuntimeException("Exam not found"));

    ExamStartDTO dto = new ExamStartDTO();
    dto.setExamId(exam.getId());
    dto.setExamTitle(exam.getTitle());
    dto.setDurationMinutes(exam.getDuration());
    dto.setInstructions(exam.getInstructions());

    List<ExamStartDTO.QuestionDTO> questionList = new ArrayList<>();

    for (Question q : exam.getQuestions()) {

        ExamStartDTO.QuestionDTO qdto = new ExamStartDTO.QuestionDTO();
        qdto.setId(q.getId());
        qdto.setQuestionText(q.getTitle());

        List<ExamStartDTO.OptionDTO> optList = new ArrayList<>();
        for (Option o : q.getOptions()) {
            ExamStartDTO.OptionDTO odto = new ExamStartDTO.OptionDTO();
            odto.setId(o.getId());
            odto.setOptionLabel(o.getOptionLabel());
            odto.setOptionValue(o.getOptionValue());
            optList.add(odto);
        }

        qdto.setOptions(optList);
        questionList.add(qdto);
    }

    dto.setQuestions(questionList);
    return dto;
}

}
