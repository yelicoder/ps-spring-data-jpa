package com.pluralsight.conferencedemo.models;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="time_slots")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="time_slot_id")
    private Long timeSlotId;

    @Column(name="time_slot_date")
    private Date timeSlotDate;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

    @Column(name="is_keynote_time_slot")
    private Boolean isKeyNoteTimeSlot;

    public Long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Date getTimeSlotDate() {
        return timeSlotDate;
    }

    public void setTimeSlotDate(Date timeSlotDate) {
        this.timeSlotDate = timeSlotDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getKeyNoteTimeSlot() {
        return isKeyNoteTimeSlot;
    }

    public void setKeyNoteTimeSlot(Boolean keyNoteTimeSlot) {
        isKeyNoteTimeSlot = keyNoteTimeSlot;
    }
}
