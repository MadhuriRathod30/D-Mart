package com.example.PickingService.infra;

import com.example.PickingService.model.PickWork;

public interface EventService {
    public void sendPickCompleteEvent(PickWork pickWork);
}
