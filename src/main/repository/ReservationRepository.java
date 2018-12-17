package main.repository;

import main.database.Database;
import main.entity.ReservationEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    public static Database db;

    public List<ReservationEntity> getOwner(String owner){
        if(owner == null){
            return null;
        }
        List<ReservationEntity> reservationList = new ArrayList<>();
        for(List list : db.reservations.values()){
            if(owner.equals(list.get(2))){
                LocalTime startTime = LocalTime.parse(list.get(0) + "");
                LocalTime endTime = LocalTime.parse(list.get(1) + "");
                ReservationEntity reservationEntity = new ReservationEntity(startTime, endTime, owner);
                reservationList.add(reservationEntity);
            }
        }
        return reservationList;
    }
}
