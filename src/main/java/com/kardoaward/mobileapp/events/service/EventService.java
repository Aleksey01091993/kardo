package com.kardoaward.mobileapp.events.service;

import com.kardoaward.mobileapp.events.dto.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.response.*;
import com.kardoaward.mobileapp.events.model.Event;

import java.util.List;

public interface EventService {

    void create(CreateEventDtoRequest eventDto);

    void update(UpdateEventDtoRequest eventDto);

    List<EventNameDtoResponse> findAllName();

    List<EventToEpicDtoResponse> findAllToEpic();

    void delete(Long id);

    EventFullDtoResponse findByIdEventFullDto(Long id);

    List<EventFullDtoResponse> findAllEventFullDto();
}
