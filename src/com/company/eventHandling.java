package com.company;
import com.company.objects.events;
import com.company.objects.guessSpeaker;
import java.util.ArrayList;
public class eventHandling {

    public events addGuessSpeaker(String speakerName, String eventTimeAndDate, String eventName,ArrayList<guessSpeaker> CurrentEvents){
        guessSpeaker mySpeaker = new guessSpeaker(speakerName,eventTimeAndDate,eventName);
        events events = new events(CurrentEvents);
        events.addEvent(mySpeaker);
        return events;


    }

}
