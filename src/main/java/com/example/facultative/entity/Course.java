package com.example.facultative.entity;

import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.entity.enums.Languages;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "course")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String courseName;
    @NotNull
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Collection<User> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @NotNull
    private User teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    @NotNull
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Enumerated(EnumType.STRING)
    private Languages language;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

}
