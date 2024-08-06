package com.kardoaward.mobileapp.request.repository;

import com.kardoaward.mobileapp.request.model.RequestStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    Optional<RequestStage> findByStage_IdAndRequest_Id(Long stageId, Long requestId);

    Optional<RequestStage> findFirstByRequest_IdAndStage_EndAfterOrderByStage_StartAsc(Long requestId, LocalDate end);
}
