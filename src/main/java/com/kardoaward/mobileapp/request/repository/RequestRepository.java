package com.kardoaward.mobileapp.request.repository;


import com.kardoaward.mobileapp.request.model.Request;
import com.kardoaward.mobileapp.status.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findByRequester_Id(Long id);

    Optional<Request> findByRequester_IdAndEvent_Id(Long userId, Long eventId);

    List<Request> findAllByRequester_IdAndStatusToUserInAndEvent_EndAfter(Long requesterId, List<UserStatus> status, LocalDate end);

    List<Request> findAllByRequester_IdAndStatusToUserOrEvent_EndBefore(Long requesterId, UserStatus status, LocalDate start);
}
