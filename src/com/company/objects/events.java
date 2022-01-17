package com.company.objects;
import java.util.ArrayList;
public class events {
    public ArrayList<guessSpeaker> events = new ArrayList<>();

    public events(ArrayList<guessSpeaker> events) {
        this.events = events;
    }

    public ArrayList<guessSpeaker> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<guessSpeaker> events) {
        this.events = events;
    }
    public void addEvent(guessSpeaker item) {
        this.events.add(item);
    }

}
