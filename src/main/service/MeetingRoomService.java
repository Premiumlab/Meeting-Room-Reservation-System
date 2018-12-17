package main.service;

import main.entity.ReservationEntity;
import main.entity.RoomEntity;

import java.time.LocalTime;
import java.util.List;

public interface MeetingRoomService{

    /*Returns all the rooms*/
    List<RoomEntity> getRooms();

    /*Reserves any available room for the given time range. It also handles "no availability" appropriately*/
    void reserve(LocalTime start, LocalTime end, String owner);

    /*Checks if any room is available for the given time range.*/
    boolean isAvailable(LocalTime start, LocalTime end);

    /*Fetches all reservations that are owned by the given owner.*/
    List<ReservationEntity> getReservations(String owner);

    /*Cancels all reservations owned by the owner for the given time range*/
    void cancel(String owner, LocalTime start, LocalTime end);
}