package fall2020.mobileapp.lab6_krimal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.widget.Toast;

public class BroadCastReceiver extends BroadcastReceiver {
    //private android.os.Handler handler = new Handler();
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Charger Connected", Toast.LENGTH_LONG).show();

            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        }

        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Charger Disconnected", Toast.LENGTH_SHORT).show();

            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        }

    }
   /* private void sleep(int number){
        handler.postDelayed(mToastRunnable, number);
    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };*/
}
