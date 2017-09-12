package jnp.jnpproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private double addTip(double moneyAmount, double tipPercent) {
        return moneyAmount * (1 + tipPercent/100);
    }

    private double splitBill(double sum, Integer participants) {
        return sum/participants;
    }

    private void equalSplitHandler(View view,
                                   double moneyAmount,
                                   double tipPercent,
                                   Integer participants) {
        double withTip = addTip(moneyAmount, tipPercent);
        double splitted = splitBill(withTip, participants);
        Intent myIntent = new Intent(view.getContext(), Wynik.class);
        myIntent.putExtra("withTip", withTip);
        myIntent.putExtra("splittedBill", splitted);
        startActivityForResult(myIntent, 0);
    }

    private void unequalSplitHandler(View view,
                                     double moneyAmount,
                                     double tipPercent,
                                     Integer participants) {
        Intent myIntent = new Intent(view.getContext(), NierownyWynik.class);
        myIntent.putExtra("moneyAmount", moneyAmount);
        myIntent.putExtra("tipPercent", tipPercent);
        myIntent.putExtra("participants", participants);
        startActivityForResult(myIntent, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button result = (Button) findViewById(R.id.resultButton);
        final CheckBox checkBox = (CheckBox)  findViewById(R.id.checkBox);
        checkBox.setChecked(true);


        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText moneyAmountField = (EditText) findViewById(R.id.moneyAmount);
                EditText tipPercentField  = (EditText) findViewById(R.id.TipPercent);
                Spinner participantsField  = (Spinner) findViewById(R.id.spinner);

                double moneyAmount = Double.parseDouble(moneyAmountField.getText().toString());
                double tipPercent = Double.parseDouble(tipPercentField.getText().toString());
                Integer participants = Integer.parseInt(participantsField.getSelectedItem().toString());

                if (checkBox.isChecked()) {
                    equalSplitHandler(view, moneyAmount, tipPercent, participants);
                } else {
                    unequalSplitHandler(view, moneyAmount, tipPercent, participants);
                }
            }
        });
    }
}
