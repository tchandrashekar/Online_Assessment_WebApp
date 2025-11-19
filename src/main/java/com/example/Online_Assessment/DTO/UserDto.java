
package com.example.Online_Assessment.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    
    private Long id;
    private String name;
    private String email;
}
