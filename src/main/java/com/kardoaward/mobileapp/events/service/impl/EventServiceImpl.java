package com.kardoaward.mobileapp.events.service.impl;

import com.kardoaward.mobileapp.events.dto.event.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.response.EventDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventShortDtoResponse;
import com.kardoaward.mobileapp.events.exeption.LocalDateRequestException;
import com.kardoaward.mobileapp.events.exeption.NullRequestException;
import com.kardoaward.mobileapp.events.mapper.EventMapper;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.model.Stage;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.events.repository.StageRepository;
import com.kardoaward.mobileapp.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final StageRepository stageRepository;

    @Override
    @Transactional
    public void create(CreateEventDtoRequest eventDto) {
        isDate(eventDto.getEndDate(), eventDto.getStartDate());
        eventRepository.save(EventMapper.create(eventDto));
    }

    @Override
    @Transactional
    public void update(UpdateEventDtoRequest eventDto) {
        isUpdate(eventDto);
        eventRepository.save(EventMapper.update(eventDto, findById(eventDto.getId())));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eventRepository.delete(findById(id));
    }

    @Override
    public List<EventShortDtoResponse> findAllShortsDto() {
        return EventMapper.findAllShorts(updateStatus(eventRepository.findAll()));
    }

    @Override
    public EventShortDtoResponse findByEventShortId(Long id) {
        return EventMapper.findShorts(updateStatus(List.of(findById(id))).get(0));

    }

    @Override
    public EventDtoResponse findByEventDtoId(Long id) {
        return EventMapper.findEventDto(updateStatus(List.of(findById(id))).get(0));

    }

    @Override
    public List<EventDtoResponse> findByAllEventDto() {
        return EventMapper.findAllEventsDto(updateStatus(eventRepository.findAll()));
    }

    @Override
    public EventFullDtoResponse findByIdEventFullDto(Long id) {
        return EventMapper.findFullDto(updateStatus(List.of(findById(id))).get(0));

    }

    @Override
    public List<EventFullDtoResponse> findAllEventFullDto() {
        return EventMapper.findAllFullDto(updateStatus(eventRepository.findAll()));
    }

    @Override
    @Transactional
    public List<Event> updateStatus(List<Event> events) {
        for (Event event : events) {
            if (event.getEnd().equals(LocalDate.now())) {
                event.setEventStatus("completed");
            }
            if (!event.getStages().isEmpty()) {
                for (Stage stage : event.getStages()) {
                    if (stage.getEnd().isBefore(LocalDate.now())) {
                        stage.setStageStatus("completed");
                    }
                }
            }
        }
        return eventRepository.saveAll(events);
    }

    private Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new NullRequestException("Event with id " + id + " not found")
        );
    }

    private void isUpdate(final UpdateEventDtoRequest updateEvent) {
        isDate(updateEvent.getEndDate(), updateEvent.getStartDate());
        if (!stageRepository.findByEvent_IdAndStartBeforeOrEndAfter
                (updateEvent.getId(), updateEvent.getStartDate(), updateEvent.getEndDate()).isEmpty()) {
            throw new LocalDateRequestException("The time of the event goes beyond the time frame of the stages");
        }
    }

    private void isDate(final LocalDate end, final LocalDate start) {
        if (end.isBefore(start)) {
            throw new LocalDateRequestException("the start date of the event must be later today," +
                    " the end date of the event must be later than the start date of the event");
        }
    }

}
