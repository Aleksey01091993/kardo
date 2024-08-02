package com.kardoaward.mobileapp.stage.repository;

import com.kardoaward.mobileapp.stage.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    List<Stage> findByEvent_IdAndStartBeforeOrEndAfter(Long eventId, LocalDate start, LocalDate end);
    List<Stage> findByEvent_IdAndStartBetweenOrEndBetween(Long eventId, LocalDate dateS, LocalDate dateE, LocalDate dateF, LocalDate dateG);
    Optional<Stage> findByIdAndEvent_Id(Long id, Long eventId);
}
