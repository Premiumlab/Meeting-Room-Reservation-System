package main.database;

import java.util.HashMap;
import java.util.List;

public class Database {

    //rooms: roomID
    public static HashMap<String, String> rooms;
    //reservations: (key)reservationID, (value)startTime, endTime, owner
    public static HashMap<String, List<String>> reservations;
    //rooms_reservations: (key)roomID, (value)reservationID
    public static HashMap<String, String> rooms_reservations;

    static {
        rooms = new HashMap<>();
        rooms.put("1", "R1Information");
        rooms.put("2", "R2Information");
        rooms.put("3", "R3Information");
        rooms.put("4", "R4Information");

        reservations = new HashMap<>();
        rooms_reservations = new HashMap<>();
    }
}
