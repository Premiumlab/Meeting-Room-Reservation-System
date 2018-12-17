package main.entity;

import java.time.LocalTime;

public class ReservationEntity {

    private LocalTime start;
    private LocalTime end;
    private String owner;

    public ReservationEntity(LocalTime start, LocalTime end, String owner){
        this.start = start;
        this.end = end;
        this.owner = owner;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
