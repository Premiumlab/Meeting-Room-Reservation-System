package main.repository;

import main.database.Database;
import main.entity.RoomEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    public static Database db;

    public List<RoomEntity> getRooms(){
        List<RoomEntity> roomList = new ArrayList<>();
        for (String roomID : db.rooms.keySet()){
            RoomEntity room = new RoomEntity(roomID);
            roomList.add(room);
        }
        return roomList;
    }

}
