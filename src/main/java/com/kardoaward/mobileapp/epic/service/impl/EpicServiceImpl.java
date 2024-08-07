package com.kardoaward.mobileapp.epic.service.impl;


import com.kardoaward.mobileapp.epic.dto.request.CreateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.request.UpdateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;
import com.kardoaward.mobileapp.epic.mapper.EpicMapper;
import com.kardoaward.mobileapp.epic.model.Epic;
import com.kardoaward.mobileapp.epic.repository.EpicRepository;
import com.kardoaward.mobileapp.epic.service.EpicService;
import com.kardoaward.mobileapp.exeption.NullRequestException;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void addToEvent(Long eventId, CreateEpicToEvent epic) {
        epicRepository.save(EpicMapper.mapCreate(findByEventId(eventId), epic));
    }

    @Override
    @Transactional
    public void update(Long epicId, UpdateEpicToEvent epic) {
        epicRepository.save(EpicMapper.mapUpdate(findByEpicId(epicId), epic));
    }

    @Override
    @Transactional
    public void update(Long epicId, Long eventId, UpdateEpicToEvent epic) {
        epicRepository.save(EpicMapper.mapUpdate(findByEpicIdAndEventId(epicId, eventId), epic));
    }

    @Override
    @Transactional
    public void delete(Long epicId) {
        epicRepository.delete(findByEpicId(epicId));
    }

    @Override
    @Transactional
    public void delete(Long eventId, Long epicId) {
        epicRepository.delete(findByEpicIdAndEventId(epicId, eventId));
    }

    @Override
    public List<EpicDtoResponse> findAll() {
        return EpicMapper.mapAll(epicRepository.findAll());
    }

    @Override
    public List<EpicDtoResponse> findAllEventId(Long eventId) {
        return EpicMapper.mapAll(epicRepository.findAllByEvent_Id(eventId));
    }

    @Override
    public EpicDtoResponse findById(Long epicId) {
        return EpicMapper.mapEpicDto(findByEpicId(epicId));
    }



    private Event findByEventId(Long id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new NullRequestException("Event with id " + id + " not found")
        );
    }

    private Epic findByEpicId(Long id) {
        return epicRepository.findById(id).orElseThrow(
                () -> new NullRequestException("Epic with id " + id + " not found")
        );
    }

    private Epic findByEpicIdAndEventId(Long epicId, Long id) {
        return epicRepository.findByIdAndEvent_Id(epicId, id)
                .orElseThrow(() -> new NullRequestException("the subtask does not belong to the current task"));
    }
}
