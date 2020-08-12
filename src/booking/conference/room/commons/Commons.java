/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking.conference.room.commons;

import booking.conference.room.entities.Session;
import booking.conference.room.entities.TimeSlot;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Dhiraj Rathod
 */
public class Commons {

//    For getting time to be added for start and End to the Time Slot
    public static Date addTimeToDate(Date date, int mins) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, mins);
        return calendar.getTime();
    }

//    For getting Start End from calendar
    public static Date getEndTime(int endTime) {
        Calendar endTimeOfSlotCalendar = Calendar.getInstance();
        endTimeOfSlotCalendar.set(Calendar.HOUR_OF_DAY, endTime);
        endTimeOfSlotCalendar.set(Calendar.MINUTE, 0);
        endTimeOfSlotCalendar.set(Calendar.SECOND, 0);
        endTimeOfSlotCalendar.set(Calendar.MILLISECOND, 0);
        return endTimeOfSlotCalendar.getTime();
    }

//    For getting Start hour from calendar
    public static Date getStartTime(int startTime) {
        Calendar startTimeOfSlotCalendar = Calendar.getInstance();
        startTimeOfSlotCalendar.set(Calendar.HOUR_OF_DAY, startTime);
        startTimeOfSlotCalendar.set(Calendar.MINUTE, 0);
        startTimeOfSlotCalendar.set(Calendar.SECOND, 0);
        startTimeOfSlotCalendar.set(Calendar.MILLISECOND, 0);
        return startTimeOfSlotCalendar.getTime();
    }

//    To check if a string is number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

//    For Getting Time in number format for example 60mins will return 60
    public static int getTimeNumber(String timeMinutes) {
        int returnValue = 0;
        String afterReplace = timeMinutes.replaceAll("([^0-9])", "");
        if (isNumeric(afterReplace)) {
            returnValue = Integer.parseInt(afterReplace);
        }
        return returnValue;
    }

//    For Getting Frormatted Date in Hours and Minute with AM PM
    public static String getFrormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

//    For Getting Session Total Time in Minutes
    public static long getSessionTotalTime(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        long diffMinutes = diff / (60 * 1000);
        return diffMinutes;
    }

//    For Printing all rooms and their sessions
    public static void printAllRooms(Map<String, Map<Session, TimeSlot>> rooms) {
        rooms.entrySet().forEach((Map.Entry<String, Map<Session, TimeSlot>> entry) -> {
            String room = entry.getKey();
            Map<Session, TimeSlot> sessionsValue = entry.getValue();

            System.out.println(room);
            sessionsValue.entrySet().forEach((sessionsEntry) -> {
                TimeSlot timeSlot = sessionsEntry.getValue();
                Session meeting = sessionsEntry.getKey();
                if (meeting.getTitle().equals("Lunch")) {
                    System.out.println(Commons.getFrormattedDate(timeSlot.getBeginDate()) + " " + meeting);
                } else {
                    System.out.println(Commons.getFrormattedDate(timeSlot.getBeginDate()) + " " + meeting + " " + Commons.getSessionTotalTime(timeSlot.getBeginDate(), timeSlot.getEndDate()) + " Mins");
                }
            });
        });
    }
}
