package com.example.facultative.entity;

import com.example.facultative.entity.enums.Grade;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
