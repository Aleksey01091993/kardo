package com.kardoaward.mobileapp.request.repository;


import com.kardoaward.mobileapp.request.model.RequestEvents;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RequestRepositoryEvent extends JpaRepository<RequestEvents, Long> {
    List<RequestEvents> findAllByStatus(AdminEventStatus status);
    List<RequestEvents> findAllByStatusToUser(UserEventStatus status);
    List<RequestEvents> findAllByRequester_IdAndStatusToUserInAndEvent_EndBefore(Long requesterId, List<UserEventStatus> status, LocalDate end);
    List<RequestEvents> findAllByRequester_IdAndStatusToUserOrEvent_StartBefore(Long requesterId, UserEventStatus status, LocalDate start);
}
