package com.kardoaward.mobileapp.events.repository;

import com.kardoaward.mobileapp.events.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
}
