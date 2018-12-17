package main.service;

import main.entity.ReservationEntity;
import main.entity.RoomEntity;
import main.repository.ReservationRepository;
import main.repository.RoomRepository;
import main.repository.RoomReservationRepository;

import java.time.LocalTime;
import java.util.List;

public class MeetingRoomServiceImplementation implements MeetingRoomService {

    private RoomReservationRepository roomReservationRepository;
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    public MeetingRoomServiceImplementation(){
        this.roomReservationRepository = new RoomReservationRepository();
        this.roomRepository = new RoomRepository();
        this.reservationRepository = new ReservationRepository();
    }

    @Override
    public List<RoomEntity> getRooms(){
        return roomRepository.getRooms();
    }

    @Override
    public void reserve(LocalTime start, LocalTime end, String owner){
        if(!isValidTime(start, end)){
            return ;
        }
        roomReservationRepository.reserve(start, end, owner);
    }

    @Override
    public boolean isAvailable(LocalTime start, LocalTime end){
        if(!isValidTime(start, end)){
            return false;
        }
        return roomReservationRepository.isAvailable(start, end);
    }

    @Override
    public List<ReservationEntity> getReservations(String owner){
        if(owner == null){
            return null;
        }
        return reservationRepository.getOwner(owner);
    }

    @Override
    public void cancel(String owner, LocalTime start, LocalTime end){
        if(!isValidTime(start, end)){
            return ;
        }
        if(owner == null){
            return ;
        }
        roomReservationRepository.cancel(owner, start, end);
    }


    public boolean isValidTime(LocalTime start, LocalTime end){
        if(start == null || end == null || start.compareTo(end) > 0){
            System.out.println("Invalid start or end time. Please enter start and end time again.");
            return false;
        }else{
            return true;
        }
    }
}

