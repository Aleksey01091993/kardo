package com.kardoaward.mobileapp.request.repository;


import com.kardoaward.mobileapp.request.model.RequestEvents;
import com.kardoaward.mobileapp.status.AdminRequest;
import com.kardoaward.mobileapp.status.UserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEvents, Long> {
    List<RequestEvents> findAllByStatus(AdminRequest status);
    List<RequestEvents> findAllByStatusToUser(UserRequest status);
}
