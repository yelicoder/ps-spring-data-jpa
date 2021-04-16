package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TimeSlotJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TimeSlotTest {

    @Autowired
    public TimeSlotJpaRepository timeSlotJpaRepository;

    @Test
    public void testBefore() throws Exception {
        LocalTime localTime = LocalTime.of(11,30,0,0);
        List<TimeSlot> timeSlots = timeSlotJpaRepository.findByStartTimeBefore(localTime);

        assertTrue(timeSlots.size()>0);

        System.out.println("size = " + timeSlots.size());
    }
}
