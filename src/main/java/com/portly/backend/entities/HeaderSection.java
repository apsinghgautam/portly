package com.portly.backend.entities;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeaderSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heroDescription;

    @Column(length = 14000000)
    private String image;

    @Column(length = 14000000)
    private String cv;

    private String name;

    private String backgroundColour;
    private String textColour;
    private String highlightColour;

}
