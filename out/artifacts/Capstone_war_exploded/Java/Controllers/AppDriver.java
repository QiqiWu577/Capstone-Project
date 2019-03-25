package Controllers;

import Model.Day;
import Persistance.DBOperation;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class AppDriver {

    public static void main(String[] args) {

        PasswordManager pm = new PasswordManager();

        pm.getHashSalt(5555, "meow");

//        LocalDateTime monday = LocalDateTime.of(2019,03,11,0,0);
//        DBOperation dbops = new DBOperation();
//
//        ArrayList<Day> schedule = dbops.getSchedule(monday);
//
//        for(Day day: schedule) {
//            System.out.println(day.toString());
//        }

        //2019-03-11 12:00:00


    }
}
