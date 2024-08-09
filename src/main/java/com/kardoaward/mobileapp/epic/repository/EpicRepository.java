package com.kardoaward.mobileapp.epic.repository;

import com.kardoaward.mobileapp.epic.model.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {
    Optional<Epic> findByIdAndEvent_Id(Long id, Long eventId);
    List<Epic> findAllByEvent_Id(Long eventId);
}
