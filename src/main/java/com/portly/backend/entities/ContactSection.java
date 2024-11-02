package com.portly.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String mobile;

    @ElementCollection
    @CollectionTable(name = "user_attributes", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "attribute_value")
    private Map<String,String> socialLinks;
    private String backgroundColour;
    private String textColour;
    private String messageBackgroundColour;
    private String messageTextColour;
    private String buttonBackgroundColour;
    private String buttonTextColour;

}
