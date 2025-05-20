package com.example.PackingService.infra;

import com.example.PickingService.model.PickWork;

public interface EventService {

    public void consumePickCompleteEvent(PickWork pickwork);
}
