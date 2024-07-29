package com.kardoaward.mobileapp.events.controller;

import com.kardoaward.mobileapp.events.service.impl.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;
}
