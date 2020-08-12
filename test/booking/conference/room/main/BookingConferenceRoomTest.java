/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking.conference.room.main;

import booking.conference.room.entities.Session;
import booking.conference.room.entities.TimeSlot;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class BookingConferenceRoomTest {

    public BookingConferenceRoomTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processSessions method, of class BookingConferenceRoom.
     */
    @Test
    public void testProcessSessions() {
        int totalConferenceRooms = 2;
        int[][] timeSlotsAvailable = {{9, 12}, {1, 5}};
        LinkedHashMap<String, String> sessionsLinkedHashmap = new LinkedHashMap<>();
        sessionsLinkedHashmap.put("Writing Fast Tests Against Enterprise Rails", "60min");
        sessionsLinkedHashmap.put("Overdoing it in Python", "45min");
        sessionsLinkedHashmap.put("Lua for the Masses", "30min");
        sessionsLinkedHashmap.put("Ruby Errors from Mismatched Gem Versions", "45min");
        sessionsLinkedHashmap.put("Common Ruby Errors", "45min");
        sessionsLinkedHashmap.put("Rails for Python Developers", "5min");
        sessionsLinkedHashmap.put("Communicating Over Distance", "60min");
        sessionsLinkedHashmap.put("Accounting-Driven Development", "45min");
        sessionsLinkedHashmap.put("Woah", "30min");
        sessionsLinkedHashmap.put("Sit Down and Write", "30min");
        sessionsLinkedHashmap.put("Pair Programming vs Noise", "45min");
        sessionsLinkedHashmap.put("Rails Magic", "60min");
        sessionsLinkedHashmap.put("Ruby on Rails: Why We Should Move On", "60min");
        sessionsLinkedHashmap.put("Clojure Ate Scala (on my project)", "45min");
        sessionsLinkedHashmap.put("Programming in the Boondocks of Seattle", "30min");
        sessionsLinkedHashmap.put("Ruby vs. Clojure for Back-End Development", "30min");
        sessionsLinkedHashmap.put("Ruby on Rails Legacy App Maintenance", "60min");
        sessionsLinkedHashmap.put("A World Without HackerNews", "30min");
        sessionsLinkedHashmap.put("User Interface CSS in Rails Apps", "30min");
        Map<String, Map<Session, TimeSlot>> expResult = BookingConferenceRoom.processSessions(totalConferenceRooms, timeSlotsAvailable, sessionsLinkedHashmap);
        System.out.println("" + expResult);
        Map<String, Map<Session, TimeSlot>> result = BookingConferenceRoom.processSessions(totalConferenceRooms, timeSlotsAvailable, sessionsLinkedHashmap);
        System.out.println("" + result);
        assertEquals(expResult, result);
    }

}
