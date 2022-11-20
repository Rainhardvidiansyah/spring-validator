package com.mockito.service;

import com.mockito.entity.Ticket;
import com.mockito.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketId(Long id){
        if(id == null || id == 0){
            throw new RuntimeException("No Id Can be Found in your DB");
        }
        return ticketRepository.findById(id);
    }

    public List<Ticket> findAllTicket(){
        return ticketRepository.findAll();
    }

    public void deleteTicketById(Long id){
        if(id == null || id.equals(0L)){
            throw new RuntimeException("Id not found!");
        }
        ticketRepository.deleteById(id);
    }

    public List<String> findDesc(String desc){
        if(!desc.isEmpty()){
            List<Ticket> ticketList =
            ticketRepository.findByDescription(desc);
            return ticketList.stream().map(Ticket::getDesc).collect(Collectors.toList());
        }else{
            throw new IllegalStateException("No desc Found");
        }
    }

}
