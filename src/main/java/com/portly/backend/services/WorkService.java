package com.portly.backend.services;

import com.portly.backend.entities.Work;

public interface WorkService {

    Work createWork();

    Work updateWork(Work work);

    void deleteWork(Work work);

    Work addWork(Work work);
}
