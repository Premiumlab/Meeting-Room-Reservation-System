package test;

import main.database.Database;
import main.service.MeetingRoomServiceImplementation;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;


public class MeetingRoomServiceTest {

    public static Database db;

    //Verify that four rooms are initialized.
    @Test
    public void test1() {
        assertEquals(4, db.rooms.size());
    }

    //Verify that all the rooms can be listed.
    @Test
    public void test2(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();
        assertEquals(4, m.getRooms().size());
    }

    //Verify that a room can be reserved from one time to another time if start time is before end time.
    @Test
    public void test3(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();
        assertEquals(0, db.reservations.size());
        assertEquals(0, db.rooms_reservations.size());

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("12:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        assertEquals(1, db.reservations.size());
        assertEquals(1, db.rooms_reservations.size());
    }

    //Verify that a room can not be reserved from one time to another time if start time is after end time.
    @Test
    public void test4(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();
        assertEquals(0, db.reservations.size());
        assertEquals(0, db.rooms_reservations.size());

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("09:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        assertEquals(0, db.reservations.size());
        assertEquals(0, db.rooms_reservations.size());
    }

    //Verify that any room is available for the given time.
    @Test
    public void test5(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("23:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        LocalTime start2 = LocalTime.parse("10:11:54.274");
        LocalTime end2 = LocalTime.parse("13:12:54.274");
        assertEquals(true, m.isAvailable(start2, end2));
    }

    //Verify that no room is available when all rooms are booked for the given time.
    @Test
    public void test6(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("23:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        LocalTime start2 = LocalTime.parse("11:10:54.274");
        LocalTime end2 = LocalTime.parse("13:12:54.274");
        String owner2 = "Cathy";
        m.reserve(start2, end2, owner2);

        LocalTime start3 = LocalTime.parse("09:10:54.274");
        LocalTime end3 = LocalTime.parse("22:12:54.274");
        String owner3 = "Tom";
        m.reserve(start3, end3, owner3);

        LocalTime start4 = LocalTime.parse("01:10:54.274");
        LocalTime end4 = LocalTime.parse("14:12:54.274");
        String owner4 = "Candy";
        m.reserve(start4, end4, owner4);

        LocalTime start5 = LocalTime.parse("11:12:54.274");
        LocalTime end5 = LocalTime.parse("11:22:54.274");
        assertEquals(false, m.isAvailable(start5, end5));
    }

    //Verify that one room cannot be booked by multiple owner given a time
    @Test
    public void test7(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("23:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        LocalTime start2 = LocalTime.parse("11:10:54.274");
        LocalTime end2 = LocalTime.parse("13:12:54.274");
        String owner2 = "Cathy";
        m.reserve(start2, end2, owner2);

        LocalTime start3 = LocalTime.parse("09:10:54.274");
        LocalTime end3 = LocalTime.parse("22:12:54.274");
        String owner3 = "Tom";
        m.reserve(start3, end3, owner3);

        LocalTime start4 = LocalTime.parse("01:10:54.274");
        LocalTime end4 = LocalTime.parse("14:12:54.274");
        String owner4 = "Candy";
        m.reserve(start4, end4, owner4);

        LocalTime start5 = LocalTime.parse("11:12:54.274");
        LocalTime end5 = LocalTime.parse("11:22:54.274");
        String owner5 = "Tom";
        m.reserve(start5, end5, owner5);

        assertEquals(4, db.reservations.size());
        assertEquals(4, db.rooms_reservations.size());
    }

    //Verify that all reservations owned by the owner for the given time can be canceled.
    @Test
    public void test8(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("23:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        LocalTime start2 = LocalTime.parse("11:11:54.274");
        LocalTime end2 = LocalTime.parse("23:12:54.274");
        m.reserve(start2, end2, owner);

        m.cancel("Candy", start, end);

        assertEquals(0, db.reservations.size());
        assertEquals(0, db.rooms_reservations.size());

    }

    //Verify that all reservations can be fetched by the given owner.
    @Test
    public void test9(){
        MeetingRoomServiceImplementation m = new MeetingRoomServiceImplementation();

        LocalTime start = LocalTime.parse("11:11:54.274");
        LocalTime end = LocalTime.parse("23:12:54.274");
        String owner = "Candy";
        m.reserve(start, end, owner);

        LocalTime start2 = LocalTime.parse("11:11:54.274");
        LocalTime end2 = LocalTime.parse("22:12:54.274");
        m.reserve(start2, end2, owner);

        assertEquals(2, m.getReservations("Candy").size());

    }
}
