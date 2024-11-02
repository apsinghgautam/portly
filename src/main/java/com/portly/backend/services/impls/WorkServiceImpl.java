package com.portly.backend.services.impls;

import com.portly.backend.entities.Work;
import com.portly.backend.repositories.WorkRepository;
import com.portly.backend.services.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;

    @Override
    public Work createWork() {
        Work work = Work.builder()
                .companyName("Microsoft")
                .duration("15 july 2024 - present")
                .jobRole("Flutter developer")
                .jobDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s")
                .build();
        return workRepository.save(work);
    }

    @Override
    public Work updateWork(Work work) {
        return workRepository.save(work);
    }

    @Override
    public void deleteWork(Work work) {
        workRepository.delete(work);
    }

    @Override
    public Work addWork(Work work) {
        return workRepository.save(work);
    }
}
