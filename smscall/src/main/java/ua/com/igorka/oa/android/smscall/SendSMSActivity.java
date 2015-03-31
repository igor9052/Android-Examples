package ua.com.igorka.oa.android.smscall;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SendSMSActivity extends ActionBarActivity {

    public static final String ACTION_DELIVERY_SMS = "ua.com.igorka.oa.android.smscall.SendSMSActivity.DELIVERY_SMS";
    public static final String EXTRA_PHONE_NUMBER = "PHONE_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_send);
        Button sendSmsButton = (Button) findViewById(R.id.button_send_sms);


        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });
    }

    private void sendSms() {
        EditText phoneNumberEditText = (EditText) findViewById(R.id.edit_phone_number);
        EditText messageEditText = (EditText) findViewById(R.id.edit_text_message);
        SmsManager smsManager = SmsManager.getDefault();

        Intent deliveryIntent = new Intent(this, DeliverySmsBroadcastReceiver.class);
        deliveryIntent.putExtra(EXTRA_PHONE_NUMBER, phoneNumberEditText.getText().toString());

        PendingIntent pendingDeliveryIntent = PendingIntent.getBroadcast(this, 0, deliveryIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        try {
            smsManager.sendTextMessage(phoneNumberEditText.getText().toString()
                    , null
                    , messageEditText.getText().toString()
                    , null
                    , pendingDeliveryIntent);
            //Toast.makeText(this, "SMS sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS was NOT sent", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_send_alarm) {
            startActivity(new Intent(this, PendingIntentActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DeliverySmsBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Message was delivered to number " + intent.getStringExtra(EXTRA_PHONE_NUMBER)
                    ,Toast.LENGTH_LONG).show();

        }
    }


}
