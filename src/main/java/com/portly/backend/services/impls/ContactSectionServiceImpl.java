package com.portly.backend.services.impls;

import com.portly.backend.entities.ContactSection;
import com.portly.backend.repositories.ContactSectionRepository;
import com.portly.backend.services.ContactSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactSectionServiceImpl implements ContactSectionService {

    private final ContactSectionRepository contactSectionRepository;

    @Override
    public ContactSection createContactSection() {
        ContactSection contactSection = ContactSection.builder()
                .email("shashank1q@gmail.com")
                .mobile("1234567890")
                .socialLinks(Map.of("facebook", "https://www.facebook.com/shashank1q",
                        "instagram", "https://www.instagram.com/shashank1q",
                        "youtube", "",
                        "linkedin", "https://www.linkedin.com/in/shashank1q",
                        "twitter", "https://twitter.com/shashank1q",
                        "github", "https://github.com/shashank1q"
                    ))
                .backgroundColour("0xFFFFFFFF")
                .textColour("0xFF000000")
                .buttonBackgroundColour("0xFFB41C6F")
                .buttonTextColour("0xFFFFFFFF")
                .messageBackgroundColour("0xFFEEEEEE")
                .messageTextColour("0xFF000000")
                .build();
        return contactSectionRepository.save(contactSection);
    }

    @Override
    public ContactSection updateContactSection(ContactSection contactSection) {
        return contactSectionRepository.save(contactSection);
    }
}
