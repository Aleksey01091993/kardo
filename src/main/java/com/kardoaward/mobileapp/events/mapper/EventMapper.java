package com.kardoaward.mobileapp.events.mapper;


import com.kardoaward.mobileapp.epic.mapper.EpicMapper;
import com.kardoaward.mobileapp.events.dto.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.response.*;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.stage.mapper.StageMapper;

import java.util.List;

public class EventMapper {

    public static Event create(CreateEventDtoRequest eventDto) {
        return Event.builder()
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .start(eventDto.getStartDate())
                .end(eventDto.getEndDate())
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

    public static List<EventNameDtoResponse> mapAllNames(List<Event> events) {
        return events.stream()
                .map(o1 -> new EventNameDtoResponse(o1.getId(), o1.getName()))
                .toList();
    }

    public static EventToEpicDtoResponse mapToEpic(Event event) {
        return new EventToEpicDtoResponse(event.getId(), event.getName(), event.getDescription(),
                event.getEpics().stream().map(EpicMapper::mapEpicDto).toList());
    }

    public static List<EventToEpicDtoResponse> mapAllToEpic(List<Event> event) {
        return event.stream().map(EventMapper::mapToEpic).toList();
    }

    public static EventFullDtoResponse findFullDto(Event event) {
        return new EventFullDtoResponse(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStart(),
                event.getEnd(),
                StageMapper.findAllStageDto(event.getStages())
        );
    }

    public static List<EventFullDtoResponse> findAllFullDto(List<Event> event) {
        return event.stream().map(EventMapper::findFullDto).toList();
    }


}
