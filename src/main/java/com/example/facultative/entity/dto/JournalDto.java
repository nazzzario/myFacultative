package com.example.facultative.entity.dto;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.Grade;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class JournalDto {
    private Grade grade;

    private User user;

    private Course course;
}
