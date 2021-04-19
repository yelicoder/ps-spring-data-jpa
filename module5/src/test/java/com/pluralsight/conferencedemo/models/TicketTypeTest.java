package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TicketTypeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TicketTypeTest {

    @Autowired
    TicketTypeJpaRepository ticketTypeJpaRepository;

    @Test
    public void testTrue() {
        List<TicketType> types = ticketTypeJpaRepository.findByIncludesWorkshopTrue();
        assertTrue(types.size()>0);
    }
}
