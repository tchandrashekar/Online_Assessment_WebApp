
package com.example.Online_Assessment.Entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    
}
