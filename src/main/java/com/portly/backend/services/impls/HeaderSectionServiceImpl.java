package com.portly.backend.services.impls;

import com.portly.backend.entities.HeaderSection;
import com.portly.backend.repositories.HeaderSectionRepository;
import com.portly.backend.services.HeaderSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HeaderSectionServiceImpl implements HeaderSectionService {

    private final HeaderSectionRepository headerSectionRepository;

    @Override
    public HeaderSection createHeaderSection() {
        HeaderSection headerSection = HeaderSection.builder()
                .cv(null)
                .backgroundColour("0xFF001228")
                .image(null)
                .heroDescription("< Flutter Developer />")
                .textColour("0xFFFFFFFF")
                .highlightColour("0xFFFF2AF1")
                .build();
        return headerSectionRepository.save(headerSection);
    }

    @Override
    public HeaderSection updateHeaderSection(HeaderSection headerSection) {
        return headerSectionRepository.save(headerSection);
    }
}
