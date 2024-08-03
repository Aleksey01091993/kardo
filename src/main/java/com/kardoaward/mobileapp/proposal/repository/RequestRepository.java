package com.kardoaward.mobileapp.proposal.repository;

import com.kardoaward.mobileapp.proposal.model.Requests;
import com.kardoaward.mobileapp.status.UserEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Requests, Long> {
    List<Requests> findAllByStatusToUser(UserEventStatus status);
}
