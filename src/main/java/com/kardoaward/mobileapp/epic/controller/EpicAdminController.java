package com.kardoaward.mobileapp.epic.controller;

import com.kardoaward.mobileapp.epic.dto.request.CreateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;
import com.kardoaward.mobileapp.epic.service.EpicService;
import com.kardoaward.mobileapp.epic.dto.request.UpdateEpicToEvent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/epic")
public class EpicAdminController {

    private final EpicService epicService;


    @PostMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateEpicToEvent eventDto, @PathVariable Long eventId) {
        log.info("Пришел POST запрос /admin/epic/event/{} с телом: {}", eventId, eventDto);
        epicService.addToEvent(eventId, eventDto);
        log.info("Отправлен статус 201 без тела");
    }

    @PatchMapping("/{epicId}/event/{eventId}")
    public void update(@RequestBody @Valid UpdateEpicToEvent eventDto, @PathVariable Long eventId, @PathVariable Long epicId) {
        log.info("Пришел PATH запрос /admin/epic/{}/event/{} с телом: {}", epicId, eventId, eventDto);
        epicService.update(epicId, eventId, eventDto);
        log.info("Отправлен статус 200 без тела");
    }

    @PatchMapping("/{epicId}")
    public void update(@RequestBody @Valid UpdateEpicToEvent eventDto, @PathVariable Long epicId) {
        log.info("Пришел PATH запрос /admin/epic/{} с телом: {}", epicId, eventDto);
        epicService.update(epicId, eventDto);
        log.info("Отправлен статус 200 без тела");
    }

    @DeleteMapping("/{epicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long epicId) {
        log.info("Пришел DELETE запрос /admin/epic/{}", epicId);
        epicService.delete(epicId);
        log.info("Отправлен статус 204 без тела");
    }

    @DeleteMapping("/{epicId}/event/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long eventId, @PathVariable Long epicId) {
        log.info("Пришел DELETE запрос /admin/epic/{}/event/{}", epicId, eventId);
        epicService.delete(eventId, epicId);
        log.info("Отправлен статус 204 без тела");
    }

    @GetMapping("/all")
    public List<EpicDtoResponse> findAll() {
        log.info("Пришел GET запрос /admin/epic/all");
        final List<EpicDtoResponse> response = epicService.findAll();
        log.info("Отправлен ответ для GET запроса /admin/epic/all с телом: {}", response);
        return response;
    }


}
