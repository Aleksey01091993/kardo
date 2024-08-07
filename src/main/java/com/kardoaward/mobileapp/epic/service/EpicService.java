package com.kardoaward.mobileapp.epic.service;

import com.kardoaward.mobileapp.epic.dto.request.CreateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.request.UpdateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;

import java.util.List;

public interface EpicService {

    void addToEvent(Long eventId, CreateEpicToEvent epic);


    void update(Long epicId, UpdateEpicToEvent epic);


    void update(Long epicId, Long eventId, UpdateEpicToEvent epic);


    void delete(Long epicId);


    void delete(Long eventId, Long epicId);

    List<EpicDtoResponse> findAll();

    List<EpicDtoResponse> findAllEventId(Long eventId);

    EpicDtoResponse findById(Long epicId);
}
