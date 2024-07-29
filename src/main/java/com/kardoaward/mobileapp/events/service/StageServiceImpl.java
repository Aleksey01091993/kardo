package com.kardoaward.mobileapp.events.service;


import com.kardoaward.mobileapp.events.dto.stage.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.events.dto.stage.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.events.dto.stage.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.events.exeption.ConflictError;
import com.kardoaward.mobileapp.events.exeption.LocalDateRequestException;
import com.kardoaward.mobileapp.events.exeption.NotFoundException;
import com.kardoaward.mobileapp.events.mapper.StageMapper;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.model.Stage;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.events.repository.StageRepository;
import com.kardoaward.mobileapp.events.service.impl.StageService;
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
        isNullAndUpdate(eventId, stage);
        Event event = getEvent(eventId);
        isDate(event, stage);
        stageRepository.save(StageMapper.mapCreateStage(stage, event));
    }

    @Override
    @Transactional
    public void update(Long eventId, UpdateStageDtoRequest stage) {
        isNullAndUpdate(eventId, stage);
        Event event = getEvent(eventId);
        isDate(event, stage);
        stageRepository.save(StageMapper.mapUpdateStage(stage, getStage(stage.getId())));
    }

    @Override
    @Transactional
    public void delete(Long stageId) {
        stageRepository.delete(getStage(stageId));
    }

    @Override
    @Transactional
    public List<Stage> updateAllStatus(List<Stage> stage) {
        for (Stage s : stage) {
            if (s.getEnd().isBefore(LocalDate.now())) {
                s.setStageStatus("Completed");
            }
        }
        return stageRepository.saveAll(stage);
    }

    @Override
    public StageEventDtoResponse findByIdStageDto(Long stageId) {
        return StageMapper.findStageDto(updateAllStatus(List.of(getStage(stageId))).get(0));
    }

    @Override
    public List<StageEventDtoResponse> findAllStageDto() {
        return StageMapper.findAllStageDto(updateAllStatus(stageRepository.findAll()));
    }

    private Event getEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event not found by id: " + eventId));
    }

    private Stage getStage(Long id) {
        return stageRepository.findById(id).orElseThrow(() -> new NotFoundException("The stage is null"));
    }

    private void isDate(Event event, Object stage) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (stage instanceof CreateStageDtoRequest) {
            startDate = ((CreateStageDtoRequest) stage).getStartDate();
            endDate = ((CreateStageDtoRequest) stage).getEndDate();
        } else if (stage instanceof UpdateStageDtoRequest) {
            startDate = ((UpdateStageDtoRequest) stage).getStartDate();
            endDate = ((UpdateStageDtoRequest) stage).getEndDate();
        }
        if (startDate.isAfter(event.getStart()) || endDate.isBefore(event.getEnd()) ||
                startDate.isBefore(endDate)) {
            throw new LocalDateRequestException("The start date cannot be after the end date, the start and end dates" +
                    " of the stage must not exceed the beginning and end of the event");
        }
        if (!event.getStages().isEmpty()) {
            for (Stage s : event.getStages()) {
                if (startDate.isAfter(s.getEnd()) &&
                        startDate.isBefore(s.getStart())) {
                    throw new LocalDateRequestException("the start date of the stage overlaps with the dates from" +
                            " another stage");
                } else if (endDate.isAfter(s.getEnd()) &&
                        endDate.isBefore(s.getStart())) {
                    throw new LocalDateRequestException("the end date of the stage overlaps with the dates from another stage");
                }
            }
        }
    }

    private void isNullAndUpdate(Long id, Object object) {
        if (id == null || object == null) {
            throw new NotFoundException("The EventID or stage is null, eventId: " +
                    id + ", stage: " + object);
        }
        if (object instanceof UpdateStageDtoRequest update) {
            if (update.getId() == null) {
                throw new NotFoundException("The stageId is null");
            } else if (!update.getId().equals(id)) {
                throw new ConflictError("this stage does not belong to the event");
            }

        }
    }


}
