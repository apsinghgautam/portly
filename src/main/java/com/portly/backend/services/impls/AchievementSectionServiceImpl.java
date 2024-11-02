package com.portly.backend.services.impls;

import com.portly.backend.entities.AchievementSection;
import com.portly.backend.repositories.AchievementSectionRepository;
import com.portly.backend.services.AchievementSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementSectionServiceImpl implements AchievementSectionService {

    private final AchievementSectionRepository achievementSectionRepository;

    @Override
    public AchievementSection createAchievementSection() {
        AchievementSection achievementSection = AchievementSection.builder()
                .backgroundColour("0xFFFFFFFF")
                .textColour("0xFF000000")
                .titleBackgroundColour("0xFFEEEEEE")
                .titleTextColour("0xFF212121")
                .achievements(List.of("Max 1792 Rating on Leetcode",
                        "Achieved Problem solving Intermediate Certificate on Hackerrank test",
                        "Winner of Annual Coding Competition in College",
                        "Winner of KIMO Edge 24 Intra college quiz competition"
                ))
                .build();

        return achievementSectionRepository.save(achievementSection);
    }

    @Override
    public AchievementSection updateAchievementSection(AchievementSection achievementSection) {
        return achievementSectionRepository.save(achievementSection);
    }
}
