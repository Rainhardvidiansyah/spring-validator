package com.mockito.repository;

import com.mockito.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public Ticket findByDesc(String desc);

    @Query("select t from Ticket t where t.desc = ?1")
    List<Ticket> findByDescription(String desc);
}
