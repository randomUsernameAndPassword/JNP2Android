package jnp.jnpproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Wynik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        Intent intent = getIntent();
        double withTip = intent.getDoubleExtra("withTip", 0.00);
        withTip = Math.round( withTip * 100.0 ) / 100.0;
        double splittedBill = intent.getDoubleExtra("splittedBill", 0.00);
        splittedBill = Math.round( splittedBill * 100.0 ) / 100.0;

        TextView withTipView = (TextView) findViewById(R.id.sumWithTip);
        TextView perParticipantView = (TextView) findViewById(R.id.perParticipant);

        withTipView.append(Double.toString(withTip));
        perParticipantView.append(Double.toString(splittedBill));
    }
}
