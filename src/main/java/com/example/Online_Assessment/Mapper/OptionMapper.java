
package com.example.Online_Assessment.Mapper;

import com.example.Online_Assessment.DTO.OptionDTO;
import com.example.Online_Assessment.Entity.Option;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {
    
    public OptionDTO toDTO(Option option){
        OptionDTO dto=new OptionDTO();
        dto.setId(option.getId());
        dto.setOptionLabel(option.getOptionLabel());
        dto.setOptionValue(option.getOptionValue());
        return dto;
    }
    
    public Option toEntity(OptionDTO dto){
        Option option = new Option();
        option.setId(dto.getId());
        option.setOptionLabel(dto.getOptionLabel());
        option.setOptionValue(dto.getOptionValue());
        return option;
    }
    
}
