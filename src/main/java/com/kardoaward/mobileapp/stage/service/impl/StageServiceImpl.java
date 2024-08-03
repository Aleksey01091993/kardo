package com.kardoaward.mobileapp.stage.service.impl;


import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.exeption.ConflictError;
import com.kardoaward.mobileapp.exeption.LocalDateRequestException;
import com.kardoaward.mobileapp.exeption.NotFoundException;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.stage.mapper.StageMapper;
import com.kardoaward.mobileapp.stage.model.Stage;
import com.kardoaward.mobileapp.stage.repository.StageRepository;
import com.kardoaward.mobileapp.stage.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StageServiceImpl implements StageService {

    private final StageRepository stageRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void create(Long eventId, CreateStageDtoRequest stage) {
        Event event = getEvent(eventId);
        isCrate(event, stage);
        stageRepository.save(StageMapper.mapCreateStage(stage, event));
    }

    @Override
    @Transactional
    public void update(Long eventId, Long stageId, UpdateStageDtoRequest stage) {
        isUpdate(eventId, stageId, stage);
        stageRepository.save(StageMapper.mapUpdateStage(stage, getStage(stageId)));
    }

    @Override
    @Transactional
    public void updateById(Long stageId, UpdateStageDtoRequest stage) {
        Stage stageEntity = getStage(stageId);
        isDate(stageEntity, stage);
        stageRepository.save(StageMapper.mapUpdateStage(stage, stageEntity));
    }

    @Override
    @Transactional
    public void delete(Long stageId) {
        stageRepository.delete(getStage(stageId));
    }



    @Override
    public StageEventDtoResponse findByIdStageDto(Long stageId) {
        return StageMapper.findStageDto(getStage(stageId));
    }

    @Override
    public List<StageEventDtoResponse> findAllStageDto() {
        return StageMapper.findAllStageDto(stageRepository.findAll());
    }

    private Event getEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event not found by id: " + eventId));
    }

    private Stage getStage(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The stage is null"));
    }

    private void isCrate(final Event event, final CreateStageDtoRequest stage) {
        if (stage.getStartDate().isAfter(stage.getEndDate())) {
            throw new ConflictError("the end date cannot be earlier than the start date");
        }
        if (stage.getEndDate().isAfter(event.getEnd()) || stage.getStartDate().isBefore(event.getStart())) {
            throw new ConflictError("The stage date cannot go beyond the event");
        }
        isDate(event.getId(), stage.getStartDate(), stage.getEndDate());
    }

    private void isUpdate(final Long eventId, final Long stageId, final UpdateStageDtoRequest stage) {
        Stage stageOnce = stageRepository.findByIdAndEvent_Id(stageId, eventId)
                .orElseThrow(() -> new ConflictError("The stage does not belong to the event"));
        isDate(stageOnce, stage);
    }

    private void isDate(final Stage stage, final UpdateStageDtoRequest stageUpdate) {
        if (stageUpdate.getStartDate().isAfter(stageUpdate.getEndDate())) {
            throw new ConflictError("the end date cannot be earlier than the start date");
        }
        if (stageUpdate.getStartDate().isBefore(stage.getEvent().getStart()) ||
                stageUpdate.getEndDate().isAfter(stage.getEvent().getEnd())) {
            throw new ConflictError("The stage date cannot go beyond the event");
        }
        isDate(stage.getEvent().getId(), stageUpdate.getStartDate(), stageUpdate.getEndDate());

    }

    private void isDate(final Long eventId, final LocalDate startDate, final LocalDate endDate) {
        if (!stageRepository.findByEvent_IdAndStartBetweenOrEndBetween(eventId, startDate, endDate, startDate, endDate)
                .isEmpty()) {
            throw new LocalDateRequestException("the stage date is overlapped with other stage dates of this event");
        }
    }

}
