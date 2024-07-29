package com.kardoaward.mobileapp.events.service;

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
import com.kardoaward.mobileapp.events.service.impl.EventService;
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

    @Override
    @Transactional
    public void create(CreateEventDtoRequest eventDto) {
        isNull(eventDto);
        isLocalDate(eventDto);
        eventRepository.save(EventMapper.create(eventDto));

    }

    @Override
    @Transactional
    public void update(UpdateEventDtoRequest eventDto) {
        isNull(eventDto);
        isLocalDate(eventDto);
        eventRepository.save(EventMapper.update(eventDto, findById(eventDto.getId())));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        isNull(id);
        eventRepository.delete(findById(id));
    }

    @Override
    public List<EventShortDtoResponse> findAllShortsDto() {
        return EventMapper.findAllShorts(updateStatus(eventRepository.findAll()));
    }

    @Override
    public EventShortDtoResponse findByEventShortId(Long id) {
        isNull(id);
        return EventMapper.findShorts(updateStatus(List.of(findById(id))).get(0));

    }

    @Override
    public EventDtoResponse findByEventDtoId(Long id) {
        isNull(id);
        return EventMapper.findEventDto(updateStatus(List.of(findById(id))).get(0));

    }

    @Override
    public List<EventDtoResponse> findByAllEventDto() {
        return EventMapper.findAllEventsDto(updateStatus(eventRepository.findAll()));
    }

    @Override
    public EventFullDtoResponse findByIdEventFullDto(Long id) {
        isNull(id);
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

    private void isNull(Object object) {
        if (object == null) {
            throw new NullRequestException("Event or id cannot be null");
        }
    }

    private void isLocalDate(Object object) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (object instanceof CreateEventDtoRequest) {
            startDate = ((CreateEventDtoRequest) object).getStartDate();
            endDate = ((CreateEventDtoRequest) object).getEndDate();
        } else if (object instanceof UpdateEventDtoRequest) {
            startDate = ((UpdateEventDtoRequest) object).getStartDate();
            endDate = ((UpdateEventDtoRequest) object).getEndDate();
            Event event = findById(((UpdateEventDtoRequest) object).getId());
            if (!event.getStages().isEmpty()) {
                LocalDate stageCanceledDate = null;
                LocalDate stageStartedDate = null;
                for (Stage stage : event.getStages()) {
                    if (stageStartedDate == null || stage.getStart().isAfter(stageStartedDate)) {
                        stageStartedDate = stage.getStart();
                    }
                    if (stageCanceledDate == null || stage.getEnd().isBefore(stageCanceledDate)) {
                        stageCanceledDate = stage.getEnd();
                    }
                }
                if (startDate.isAfter(stageStartedDate) && startDate.isBefore(stageCanceledDate)) {
                    throw new LocalDateRequestException("The start date falls within the interval between the dates" +
                            " of the stages");
                }

                if (endDate.isAfter(stageStartedDate) && endDate.isBefore(stageCanceledDate)) {
                    throw new LocalDateRequestException("The end date falls within the interval between the dates" +
                            " of the stages");
                }

            }

        }
        if (startDate.isAfter(LocalDate.now()) ||
                endDate.isAfter(startDate)) {
            throw new LocalDateRequestException("the start date of the event must be later today," +
                    " the end date of the event must be later than the start date of the event");
        }
    }

}
