package com.example.shreya.swamsteras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Shreya on 4/23/18.
 */

public class csvParse {
    private ArrayList<Event> events;
    private ArrayList<Swimmer> swimmers;


    public csvParse(InputStream is) {
        events = new ArrayList<Event>();
        swimmers = new ArrayList<Swimmer>();
        readSwimmerData(is);
        readEventData(is);
        events = getEvents();
        swimmers = getSwimmers();
    }

    public void readSwimmerData(InputStream is) {
        //Read the data from the file
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                //Split line by ","
                String[] fields = line.split(",");
                Log.d("csvParseFieldLength", String.valueOf(fields.length));
                ArrayList<Race> races = new ArrayList<>();
                if (fields[9].equals(""))
                    break;
                else {
                    Swimmer s = new Swimmer(fields[10], fields[9], Integer.parseInt(fields[8]), fields[7], races);
                    swimmers.add(s);
                    s.addRace(new Race(fields[0], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), 0, null, (fields[4] + " " + fields[5])));
                }
            }
        } catch (IOException ex) {
            Log.d("csvParseSwim", "Error reading data from file on line " + line);
        }
    }

    public void readEventData(InputStream is) {
        //Read the data from the file
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                //Split line by ","
                String[] fields = line.split(",");
                Event e = new Event();
                ArrayList<String> s = new ArrayList<>();
                ArrayList<Race> r = new ArrayList<>();
                e.setEventName(fields[0]);
                e.setEventTime(null);
                e.setNames(s);
                e.setRaces(r);
                e.addRaces(new Race(fields[0], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), 0, null, (fields[4] + " " + fields[5])));
                e.addNames((fields[4] + " " + fields[5]));
                events.add(e);
                for (Swimmer swimmer : swimmers) {
                    if (swimmer.getFirstName().equals(fields[4]) && swimmer.getLastName().equals(fields[5]))
                        swimmer.addRace(new Race(fields[0], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), 0, null, (fields[4] + " " + fields[5])));
                }
            }
        } catch (IOException ex) {
            Log.d("csvParseEvent", "Error reading data from file on line " + line);
        }
    }

    public String stuffToString(String s) {
        String string = s.replace("[", " ");
        string = string.replace("]", "");
        return string;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }
}