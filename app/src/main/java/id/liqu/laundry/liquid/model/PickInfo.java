package id.liqu.laundry.liquid.model;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Time;
import java.util.Date;

/**
 * Created by iamnubs on 29/03/2018.
 */

public class PickInfo {

    private String datePick;
    private String dateReturn;
    private LatLng location;
    private String notes;

    public PickInfo() {
    }

    public PickInfo(String datePick, String dateReturn, LatLng location, String notes) {
        this.datePick = datePick;
        this.dateReturn = dateReturn;
        this.location = location;
        this.notes = notes;
    }

    public String getDatePick() {
        return datePick;
    }

    public void setDatePick(String datePick) {
        this.datePick = datePick;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
