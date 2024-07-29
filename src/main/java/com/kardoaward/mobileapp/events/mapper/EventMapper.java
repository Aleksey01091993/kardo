package com.kardoaward.mobileapp.events.mapper;


import com.kardoaward.mobileapp.events.dto.event.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.response.EventDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventShortDtoResponse;
import com.kardoaward.mobileapp.events.model.Event;

import java.util.List;

public class EventMapper {

    public static Event create(CreateEventDtoRequest eventDto) {
        return Event.builder()
                .eventStatus("active")
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .start(eventDto.getStartDate())
                .end(eventDto.getEndDate())
                .level("0%")
                .build();
    }

    public static Event update(UpdateEventDtoRequest eventDtoUpdate, Event event) {

        if (eventDtoUpdate.getName() != null) {
            event.setName(eventDtoUpdate.getName());
        }
        if (eventDtoUpdate.getDescription() != null) {
            event.setDescription(eventDtoUpdate.getDescription());
        }
        if (eventDtoUpdate.getStartDate() != null) {
            event.setStart(eventDtoUpdate.getStartDate());
        }
        if (eventDtoUpdate.getEndDate() != null) {
            event.setEnd(eventDtoUpdate.getEndDate());
        }
        return event;
    }

    public static List<EventShortDtoResponse> findAllShorts(List<Event> event) {
        return event.stream()
                .map(EventMapper::findShorts)
                .toList();
    }

    public static EventShortDtoResponse findShorts(Event event) {
        return new EventShortDtoResponse(
                event.getId(),
                event.getEventStatus(),
                event.getName(),
                event.getStart(),
                event.getEnd(),
                event.getLevel()
        );
    }

    public static EventDtoResponse findEventDto(Event event) {
        return new EventDtoResponse(
                event.getId(),
                event.getName(),
                event.getLevel(),
                event.getDescription(),
                StageMapper.findAllStageDto(event.getStages())
        );
    }

    public static List<EventDtoResponse> findAllEventsDto(List<Event> event) {
        return event.stream().map(EventMapper::findEventDto).toList();
    }

    public static EventFullDtoResponse findFullDto(Event event) {
        return new EventFullDtoResponse(
                event.getId(),
                event.getEventStatus(),
                event.getName(),
                event.getDescription(),
                event.getStart(),
                event.getEnd(),
                event.getLevel(),
                StageMapper.findAllStageDto(event.getStages())
        );
    }

    public static List<EventFullDtoResponse> findAllFullDto(List<Event> event) {
        return event.stream().map(EventMapper::findFullDto).toList();
    }


}
