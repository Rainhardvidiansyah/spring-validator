package com.mockito.service;

import com.mockito.entity.Ticket;
import com.mockito.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void beforeAll() {
        ticket.builder().desc("First");
    }

    @Test
    void createTicket() {
      Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);
      var x = ticketService.createTicket(ticket);
      assertThat(x).usingRecursiveComparison().isEqualTo(ticket);
      Mockito.verify(ticketRepository, Mockito.times(1)).save(ticket);
      Mockito.verifyNoMoreInteractions(ticketRepository);
    }

    @Test
    void createTicketDua() {
        Mockito.when(ticketRepository.save(ticket)).thenReturn(new Ticket(1L, "Test"));
        var savedTicket = ticketService.createTicket(ticket);
        Assertions.assertSame(savedTicket.getDesc(), "Test");
        Assertions.assertSame(savedTicket.getId(), 1L);
    }


    @Test
    void getTicketId() {
        Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(new Ticket(1L, "first")));
        var x = ticketService.getTicketId(1L);
        var y = x.stream().map(z ->z.getId()).collect(Collectors.toList());
        Assertions.assertNotNull(y);
    }

    @Test
    void givenNullId_ThenReturnException(){
        Long id = null;
        Long nol = 0L;
        //Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> {
            ticketService.getTicketId(nol);
        });
    }

    @Test
    void findAllTicket() {
        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket(1L, "First Data"));
        list.add(new Ticket(2L, "Second Data"));
        Mockito.when(ticketRepository.findAll()).thenReturn(list);
        Assertions.assertNotNull(list);
        Assertions.assertSame(1L, list.get(0).getId());
        var allListTicket = ticketService.findAllTicket();
        System.out.println(list);
        Assertions.assertNotNull(allListTicket);
        Mockito.verify(ticketRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteTicketById() {
        var ticket = new Ticket(1L, "First Ticket");
        doNothing().when(ticketRepository).deleteById(ticket.getId());
        ticketService.deleteTicketById(1L);
        Assertions.assertNotNull(ticket.getId());
    }

    @Test
    void idNotFoundInDeleteByIdMethod(){
        Assertions.assertThrows(RuntimeException.class, ()-> {
            ticketService.deleteTicketById(null);
        });

        Assertions.assertThrows(RuntimeException.class, ()-> {
            ticketService.deleteTicketById(0L);
        });
    }

    @Test
    void findDesc() {
        var ticket = new Ticket(1L, "desc");
        var ticket2 = new Ticket(2L, "news");
        List<Ticket> list = List.of(ticket, ticket2);
        Mockito.when(ticketRepository.findByDesc(anyString())).thenReturn(new Ticket());
        var x = ticketService.findDesc("desc");
        Assertions.assertNotNull(x);
        Mockito.verify(ticketRepository, Mockito.times(1)).findByDescription("desc");
    }

    @Test
    void findDescWithThrowingAnError() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> {
            ticketService.findDesc("");
                });
    }
}