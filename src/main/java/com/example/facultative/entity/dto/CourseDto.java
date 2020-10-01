package com.example.facultative.entity.dto;

import com.example.facultative.entity.Subject;
import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.Languages;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CourseDto {
    private Languages language;
    private String courseName;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Subject subject;
    private User teacher;

}

