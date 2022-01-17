package com.company.objects;

public class guessSpeaker {
    public String speakerName;
    public String eventTimeAndDate;
    public String eventName;

    public guessSpeaker(String speakerName, String eventTimeAndDate, String eventName) {
        this.speakerName = speakerName;
        this.eventTimeAndDate = eventTimeAndDate;
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "guessSpeaker{" +
                "speakerName='" + speakerName + '\'' +
                ", eventTimeAndDate='" + eventTimeAndDate + '\'' +
                ", eventName='" + eventName + '\'' +
                '}';
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getEventTimeAndDate() {
        return eventTimeAndDate;
    }

    public void setEventTimeAndDate(String eventTimeAndDate) {
        this.eventTimeAndDate = eventTimeAndDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
