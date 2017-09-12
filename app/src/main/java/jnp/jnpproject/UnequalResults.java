package jnp.jnpproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UnequalResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unequal_results);
        int maxParticipants = 8;

        Intent intent = getIntent();
        final double[] vals = intent.getDoubleArrayExtra("vals");
        double moneyAmount = intent.getDoubleExtra("moneyAmount", 0.00);
        moneyAmount = Math.round( moneyAmount * 100.0 ) / 100.0;
        final double tipPercent = intent.getDoubleExtra("tipPercent", 0.00);
        final int participants = intent.getIntExtra("participants", 0);
        TextView[] personArray = new TextView[maxParticipants];
        TextView[] moneyArray = new TextView[maxParticipants];

        for (int i = 1; i <= 8; i++) {
            int personID = getResources().getIdentifier("person" + i, "id", getPackageName());
            TextView pv = (TextView) findViewById(personID);
            int moneyID = getResources().getIdentifier("money" + i, "id", getPackageName());
            TextView mv = (TextView) findViewById(moneyID);
            personArray[i - 1] = pv;
            moneyArray[i - 1] = mv;

            if (i > participants) {
                pv.setVisibility(View.INVISIBLE);
                mv.setVisibility(View.INVISIBLE);
            } else {
                mv.append(Double.toString(vals[i-1]));
            }
        }
        TextView moneySum = (TextView) findViewById(R.id.moneySum);
        moneySum.append(Double.toString(moneyAmount));

    }
}
