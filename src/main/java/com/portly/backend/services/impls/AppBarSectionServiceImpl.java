package com.portly.backend.services.impls;

import com.portly.backend.entities.AppBarSection;
import com.portly.backend.repositories.AppBarSectionRepository;
import com.portly.backend.services.AppBarSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppBarSectionServiceImpl implements AppBarSectionService {

    private final AppBarSectionRepository appBarSectionRepository;

    @Override
    public AppBarSection createAppBarSection() {
        AppBarSection appBarSection = AppBarSection.builder()
                .backgroundColour("0xFFB41C6F")
                .textColour("0xFFFFFFFF")
                .build();
        return appBarSectionRepository.save(appBarSection);
    }

    @Override
    public AppBarSection updateAppBarSection(AppBarSection appBarSection) {
        return appBarSectionRepository.save(appBarSection);
    }
}
