# Meeting-Room-Reservation-System

## Background
Assume that we have several meeting rooms in an office that people would want to use. Since they are shared resources, someone would have to manually manage their access - i.e allow employees to ‘reserve’ them for a given time period.
We can do better than that - let’s build a meeting room reservation system. For simplicity, assume that we are dealing with only the current day (don’t have to worry about dates).


## Data Model
Three tables are used to capture room and reservation information.

    Table name          | Column Name
    --------------------------------------------------------------------------
    rooms               | roomID           | room information
    reservations        | reservationID    | startTime      | endTime  | owner
    rooms_reservations  | roomID           | reservationID


For simplicity, tables are stored as hash map in this project.
The hash map name is the table name, the key is the first column in the table and values are the rest of the columns.

## API Design
    * Entity:
    RoomEntity and ReservationEntity map to rooms and reservations table in the database.

    * Repository:
    Implement data access layer to database.
    Table reservations and rooms_reservations are inserted/deleted at the same time for POST/DELETE method.

    * Service:
    Implement reserving and canceling reservation, getting information of room and reservation, checking room availability.

    * Resource:
    Provides API endpoints following REST style.

## Note
    * User can only reserve one available room once at a time.
    * A reservation can be cancelled when the exact start and end time provided match up with record in the database.
    * Print statement is kept for debugging purpose and it will be removed in production.
    * Please run test cases one by one instead of running the whole test class (every test cases) all at once.

## Third-party Disclosure:
JUnit is the testing framework of the project.

## Other:
    * This project is developed in IntelliJ, SDK 1.8.
    * Author: Yinqiao (Vivian) Zheng
