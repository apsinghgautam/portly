package com.portly.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1400000)
    private String image;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    private String title;

    @Column(length = 1000)
    private String description;
    private String projectUrl;

}
