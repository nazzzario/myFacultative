package com.example.facultative.entity;

import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.entity.enums.Languages;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotBlank(message = "{course.error}")
    private String courseName;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )

    private List<User> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @NotNull(message = "{course.teacher.error}")
    private User teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    @NotNull(message = "{course.subject.error}")
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{course.language.error}")
    private Languages language;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{course.start.error}")
    @FutureOrPresent(message = "{course.date.error}")
    private LocalDate startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{course.end.error}")
    @Future(message = "{course.date.error}")
    private LocalDate endDate;

}
