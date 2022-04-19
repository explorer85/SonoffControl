package com.learntodroid.postrequestwithjson;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class PeriodicTimerService extends Service {
    public PeriodicTimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    SonoffSwitcher ss;
    private Timer switchOffTimer;
    private PeriodicTimerService.SwitchOffTimerTask switchOffTimerTask;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("","PeriodicTimerService onCreateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");


       /* Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(this, R.raw.flower_romashka);
        mPlayer.setLooping(false);*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("","PeriodicTimerService onStartCommandeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
                + intent.getStringExtra("url")
                + intent.getStringExtra("period"));

        ss = new SonoffSwitcher();
        ss.url = intent.getStringExtra("url");
        ss.switchSonoffPower(true);
        switchOffTimer = new Timer();
        switchOffTimerTask = new PeriodicTimerService.SwitchOffTimerTask();
        switchOffTimer.schedule(switchOffTimerTask, 0, Long.parseLong(intent.getStringExtra("period")) * 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("","PeriodicTimerService onDestroyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        switchOffTimer.cancel();
        ss.switchSonoffPower(false);
    }

    private boolean isOn = false;
    class SwitchOffTimerTask extends TimerTask {
                @Override
                public void run() {
                    Log.i("","timer runnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                    //textResponse.setText("");
                    isOn = !isOn;
                    ss.switchSonoffPower(isOn);

        }
    }
}