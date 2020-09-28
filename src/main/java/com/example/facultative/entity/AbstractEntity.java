package com.example.facultative.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "created")
    private LocalDateTime createdDate;
    @Column(name = "updated")
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist(){
        createdDate = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    public void preUpdate(){
        updatedDate = LocalDateTime.now(ZoneOffset.UTC);
    }

    public AbstractEntity() {
    }
}
