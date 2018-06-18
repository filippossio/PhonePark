package siokouros.filippos.phonepark.Model;

import java.sql.Time;

public class Parking {

    private String Location;
    private Time startTime;
    private double duration;
    private String regNumber;

    public Parking() {
    }

    public Parking(String location, Time startTime, double duration, String regNumber) {
        Location = location;
        this.startTime = startTime;
        this.duration = duration;
        this.regNumber = regNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
}
