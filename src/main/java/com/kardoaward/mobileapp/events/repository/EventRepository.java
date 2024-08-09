package com.kardoaward.mobileapp.events.repository;

import com.kardoaward.mobileapp.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
