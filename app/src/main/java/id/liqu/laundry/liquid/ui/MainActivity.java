package id.liqu.laundry.liquid.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.orhanobut.hawk.Hawk;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.OnClick;
import id.liqu.laundry.liquid.Constants;
import id.liqu.laundry.liquid.R;
import id.liqu.laundry.liquid.Utils;
import id.liqu.laundry.liquid.model.PickInfo;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PickInfo pickInfo = new PickInfo();
    int PLACE_PICKER_REQUEST = 1337;

    Calendar calendar = Calendar.getInstance();

    Calendar pickupDate = new GregorianCalendar();
    Calendar returnDate = new GregorianCalendar();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.t_date)
    TextInputEditText date;
    @BindView(R.id.t_time)
    TextInputEditText time;

    @BindView(R.id.t_date2)
    TextInputEditText date2;
    @BindView(R.id.t_time2)
    TextInputEditText time2;

    @BindView(R.id.t_location)
    TextInputEditText location;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        date.setText(Utils.getToday());
        time.setText(Utils.getHours());
        date2.setText(Utils.getToday());
        time2.setText(Utils.getHours());

    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.laundry) {
            // Handle the camera action
        } else if (id == R.id.order) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.submit)
    void submit() {
        startActivity(new Intent(this, PricingActivity.class));
    }

    @OnClick(R.id.t_date)
    void date() {
        setDate(date, pickupDate);
    }

    @OnClick(R.id.t_time)
    void time() {
        setTime(time, pickupDate);
    }

    @OnClick(R.id.t_date2)
    void date2() {
        setDate(date2, returnDate);
    }

    @OnClick(R.id.t_time2)
    void time2() {
        setTime(time2, returnDate);
    }


    void setDate(final TextInputEditText textInputEditText, final Calendar cal) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textInputEditText.setText(Utils.formatDate(year, month, dayOfMonth));
                cal.set(year, month, dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    void setTime(final TextInputEditText textInputEditText, final Calendar cal) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textInputEditText.setText(Utils.formatHours(hourOfDay, minute));
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), 0, true).show();
    }

    @OnClick(R.id.t_location)
    void pickLocation() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Toast.makeText(this, "Google Play Service are Not Available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                pickInfo.setLocation(place.getLatLng());
                location.setText(place.getAddress());
            }
        }
    }
}
