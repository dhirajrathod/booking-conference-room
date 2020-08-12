/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking.conference.room.main;

/**
 *
 * @author Dhiraj Rathod
 */
import booking.conference.room.commons.Commons;
import booking.conference.room.entities.Session;
import booking.conference.room.entities.TimeSlot;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookingConferenceRoom {

    //Process Begins Here
    public static Map<String, Map<Session, TimeSlot>> processSessions(int totalConferenceRooms, int timeSlotsAvailable[][], LinkedHashMap<String, String> sessionsLinkedHashmap) {

        Map<Session, TimeSlot> sessions = new LinkedHashMap<>();
        Map<String, Map<Session, TimeSlot>> rooms = new LinkedHashMap<>();

        for (int i = 1; i <= totalConferenceRooms; i++) {
            rooms.put("Room " + i, sessions);
        }

        for (Map.Entry<String, Map<Session, TimeSlot>> roomEntry : rooms.entrySet()) {
            String room = roomEntry.getKey();
            Map<Session, TimeSlot> sessionsLocal = new LinkedHashMap<>();

            for (int i = 0; i < timeSlotsAvailable.length; i++) {
                int[] timeSlotAvailable = timeSlotsAvailable[i];
                int startTimeOfSlot = 0;
                int endTimeOfSlot = 0;
                Date endTimeOfSlotDate;
                for (int j = 0; j < timeSlotAvailable.length; j++) {
                    if (j == 0) {
                        startTimeOfSlot = timeSlotAvailable[j];
                    } else if (j == 1) {
                        endTimeOfSlot = timeSlotAvailable[j];
                    }
                }
                endTimeOfSlotDate = Commons.getEndTime(endTimeOfSlot);
                Date startTime = Commons.getStartTime(startTimeOfSlot);

                for (Map.Entry<String, String> entry : sessionsLinkedHashmap.entrySet()) {
                    if (startTime.compareTo(endTimeOfSlotDate) < 0) {
                        String currentSessionName = entry.getKey();
                        String currentSessionDuration = entry.getValue();
                        Date endTime = Commons.addTimeToDate(startTime, Commons.getTimeNumber(currentSessionDuration));
                        if (endTime.compareTo(endTimeOfSlotDate) <= 0) {
                            if (!sessions.containsKey(new Session(currentSessionName))) {
                                sessions.put(new Session(currentSessionName), TimeSlot.createNewSlot(startTime, endTime));
                                sessionsLocal.put(new Session(currentSessionName), TimeSlot.createNewSlot(startTime, endTime));
                                startTime = Commons.addTimeToDate(startTime, Commons.getTimeNumber(currentSessionDuration));
                            }
                        } else {
                            if (timeSlotAvailable.length > (i + 1)) {
                                Date breakTime = endTimeOfSlotDate;
                                if (!sessionsLocal.containsKey(new Session("Lunch"))) {
                                    sessionsLocal.put(new Session("Lunch"), TimeSlot.createNewSlot(breakTime, breakTime));
                                }
                                startTime = Commons.addTimeToDate(startTime, Commons.getTimeNumber("60Mins"));
                                break;
                            }
                        }
                    } else {
                        if (timeSlotAvailable.length > (i + 1)) {
                            Date breakTime = endTimeOfSlotDate;
                            if (!sessionsLocal.containsKey(new Session("Lunch"))) {
                                sessionsLocal.put(new Session("Lunch"), TimeSlot.createNewSlot(breakTime, breakTime));
                            }
                            startTime = Commons.addTimeToDate(startTime, Commons.getTimeNumber("60Mins"));
                            break;
                        }
                    }
                }
            }

            rooms.put(room, sessionsLocal);
        }

        return rooms;
    }
}
