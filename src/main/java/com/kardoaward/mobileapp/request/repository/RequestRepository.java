package com.kardoaward.mobileapp.request.repository;


import com.kardoaward.mobileapp.request.model.Request;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByStatus(AdminEventStatus status);

    List<Request> findAllByStatusToUser(UserStatus status);

    Optional<Request> findByRequester_IdAndEvent_Id(Long userId, Long eventId);

    List<Request> findAllByRequester_IdAndStatusToUserInAndEvent_EndBefore(Long requesterId, List<UserStatus> status, LocalDate end);

    List<Request> findAllByRequester_IdAndStatusToUserOrEvent_StartBefore(Long requesterId, UserStatus status, LocalDate start);
}
