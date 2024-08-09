package com.kardoaward.mobileapp.epic.mapper;

import com.kardoaward.mobileapp.epic.dto.request.UpdateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.request.CreateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;
import com.kardoaward.mobileapp.epic.model.Epic;
import com.kardoaward.mobileapp.events.model.Event;

import java.util.List;

public class EpicMapper {

    public static Epic mapCreate(Event event, CreateEpicToEvent epic) {
        return Epic.builder()
                .name(epic.getName())
                .description(epic.getDescription())
                .event(event)
                .build();
    }

    public static Epic mapUpdate(Epic epic, UpdateEpicToEvent epicUp) {
        if (epicUp.getDescription() != null) {
            epic.setDescription(epicUp.getDescription());
        }
        if (epicUp.getName() != null) {
            epic.setName(epicUp.getName());
        }
        return epic;
    }

    public static EpicDtoResponse mapEpicDto(Epic epic) {
        return new EpicDtoResponse(
                epic.getId(),
                epic.getName(),
                epic.getDescription()
        );
    }

    public static List<EpicDtoResponse> mapAll(List<Epic> epics) {
        return epics.stream().map(EpicMapper::mapEpicDto).toList();
    }


}
