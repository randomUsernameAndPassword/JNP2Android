package jnp.jnpproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NierownyWynik extends AppCompatActivity {
    private double[] getParticipantValues(EditText[] editTextArray) {
        double[] vals = new double[editTextArray.length];
        for (int i = 0; i < editTextArray.length; i++) {
            vals[i] = Double.parseDouble(editTextArray[i].getText().toString());
        }
        return vals;
    }

    private double getSum(double[] vals) {
        double sum = 0.0;
        for (double i: vals)
            sum += i;
        return sum;
    }

    private void wrongInputHandler(double moneyAmount) {
        Context context = getApplicationContext();
        CharSequence text = "Wartości muszą się sumować do: " + Double.toString(moneyAmount);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void properInputHandler(View view,
                                    double[] vals,
                                    double moneyAmount,
                                    double tipPercent,
                                    int participants) {
        for (int i = 0; i < vals.length; i++)
            vals[i] = vals[i] * (1 + tipPercent/100);

        moneyAmount = moneyAmount * (1 + tipPercent/100);
        Intent myIntent = new Intent(view.getContext(), UnequalResults.class);
        myIntent.putExtra("vals", vals);
        myIntent.putExtra("moneyAmount", moneyAmount);
        myIntent.putExtra("tipPercent", tipPercent);
        myIntent.putExtra("participants", participants);
        startActivityForResult(myIntent, 0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nierowny_wynik);

        int maxParticipants = 8;

        Button result = (Button) findViewById(R.id.button);
        Intent intent = getIntent();
        final double moneyAmount = intent.getDoubleExtra("moneyAmount", 0.00);
        final double tipPercent = intent.getDoubleExtra("tipPercent", 0.00);
        final int participants = intent.getIntExtra("participants", 0);
        TextView[] textViewArray = new TextView[maxParticipants];
        final EditText[] editTextArray = new EditText[maxParticipants];

        for (int i = 1; i <= 8; i++) {
            int textID = getResources().getIdentifier("person" + i, "id", getPackageName());
            TextView tv = (TextView) findViewById(textID);
            int editID = getResources().getIdentifier("money" + i, "id", getPackageName());
            EditText ev = (EditText) findViewById(editID);
            textViewArray[i-1] = tv;
            editTextArray[i-1] = ev;

            if (i > participants) {
                tv.setVisibility(View.INVISIBLE);
                ev.setVisibility(View.INVISIBLE);
            }
        }

        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            double[] vals = getParticipantValues(editTextArray);
            if (getSum(vals) != moneyAmount) {
                wrongInputHandler(moneyAmount);
            } else {
              properInputHandler(view, vals, moneyAmount, tipPercent, participants);
            }
            }
        });
    }
}
