package com.kardoaward.mobileapp.events.controller;

import com.kardoaward.mobileapp.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventUserController {

    private final EventService eventService;
}
