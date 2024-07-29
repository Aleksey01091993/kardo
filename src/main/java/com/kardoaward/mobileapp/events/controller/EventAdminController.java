package com.kardoaward.mobileapp.events.controller;

import com.kardoaward.mobileapp.events.service.impl.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class EventAdminController {

    private final EventService eventService;
}
