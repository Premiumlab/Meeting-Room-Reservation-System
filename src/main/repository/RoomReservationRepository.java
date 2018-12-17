package main.repository;

import main.database.Database;
import java.time.LocalTime;
import java.util.*;

public class RoomReservationRepository {

    public static Database db;

    public void insert(LocalTime start, LocalTime end, String owner, String roomID){
        //Insert into reservations table.
        List<String> list = new ArrayList<>();
        list.add(start + "");
        list.add(end + "");
        list.add(owner);
        String reservationID = UUID.randomUUID().toString();

        //Insert into reservations table.
        db.reservations.put(reservationID, list);

        //Insert into rooms_reservations table.
        db.rooms_reservations.put(roomID, reservationID);
    }


    public boolean isAvailableByID(LocalTime start, LocalTime end, String roomID){
        if(!db.rooms_reservations.containsKey(roomID)){
            return true;
        }else{
            String reservationID = db.rooms_reservations.get(roomID);
            LocalTime roomStartTime = LocalTime.parse(db.reservations.get(reservationID).get(0));
            LocalTime roomEndTime = LocalTime.parse(db.reservations.get(reservationID).get(1));
            if (roomEndTime.compareTo(start) <= 0 || end.compareTo(roomStartTime) <= 0){
                return true;
            }
        }
        return false;
    }

    public void reserve(LocalTime start, LocalTime end, String owner){
        for(String roomID : db.rooms.keySet()){
            if(isAvailableByID(start, end, roomID)){
                insert(start, end, owner, roomID);
                System.out.println("Successful. Room ID: " + roomID + " is reserved for you." );
                return ;
            }
        }
        System.out.println("No such room is found. Please enter start and end time again.");
    }

    public boolean isAvailable(LocalTime start, LocalTime end){
        for(String roomID : db.rooms.keySet()){
            if(isAvailableByID(start, end, roomID)){
                return true;
            }
        }
        return false;
    }

    public void cancel(String owner, LocalTime start, LocalTime end){
        Iterator<String> it_ = db.reservations.keySet().iterator();
        while(it_.hasNext()){
            String reservationID = it_.next();
            List list = db.reservations.get(reservationID);
            if((start + "").equals(list.get(0)) && (end + "").equals(list.get(1)) && owner.equals(list.get(2))) {

                //Remove from reservations table.
                it_.remove();

                //Remove from rooms_reservations table.
                Iterator<Map.Entry<String,String>> it = db.rooms_reservations.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, String> entry = it.next();
                    if(reservationID.equals(entry.getValue())){
                        it.remove();
                        System.out.println("Successful. Room ID: " + entry.getKey() + " is canceled for you.");
                    }
                }
            }
        }
    }
}
