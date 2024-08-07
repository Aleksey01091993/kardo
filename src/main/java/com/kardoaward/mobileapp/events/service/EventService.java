package com.kardoaward.mobileapp.events.service;

import com.kardoaward.mobileapp.events.dto.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.response.EventNameDtoResponse;
import com.kardoaward.mobileapp.events.dto.response.EventToEpicDtoResponse;

import java.util.List;

public interface EventService {

    void create(CreateEventDtoRequest eventDto);

    void update(UpdateEventDtoRequest eventDto, Long eventId);

    List<EventNameDtoResponse> findAllName();

    List<EventToEpicDtoResponse> findAllToEpic();

    void delete(Long id);

    EventFullDtoResponse findByIdEventFullDto(Long id);

    List<EventFullDtoResponse> findAllEventFullDto();
}
