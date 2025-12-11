
package com.example.Online_Assessment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String message;
    private Long userId;
    private String role;
}
