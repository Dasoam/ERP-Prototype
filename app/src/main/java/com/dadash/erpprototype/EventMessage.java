package com.dadash.erpprototype;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventMessage {
    private String message;
    private String postedBy;
    private long timestamp;
    private String title;

    // Empty constructor needed for Firestore deserialization
    public EventMessage() {}

    public EventMessage(String message, String postedBy, long timestamp, String title) {
        this.message = message;
        this.postedBy = postedBy;
        this.timestamp = timestamp;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}
