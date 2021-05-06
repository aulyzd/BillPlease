package sg.edu.rp.c346.id20014198.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numPax;
    ToggleButton svc;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup paymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextAmount);
        numPax = findViewById(R.id.editTextPax);
        totalBill = findViewById(R.id.textViewTotalBill);
        eachPays = findViewById(R.id.textViewSplitAmount);
        svc = findViewById(R.id.toggleButtonSVC);
        gst = findViewById(R.id.toggleButtonGST);
        split= findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        discount = findViewById(R.id.editTextDiscount);
        paymentMethod = findViewById(R.id.radioGroupPayment);

        split.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

               if (amount.getText().toString().length() != 0 && numPax.getText().toString().length() != 0) {
                   double newAmt = 0.0;

                   if (!svc.isChecked() && !gst.isChecked()) {
                       newAmt = Double.parseDouble(amount.getText().toString());
                   } else if (svc.isChecked() && !gst.isChecked()) {
                       newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                   } else if (!svc.isChecked() && gst.isChecked()) {
                       newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                   } else {
                       newAmt = Double.parseDouble(amount.getText().toString()) * 1.7;
                   }
                   if (discount.getText().toString().length() != 0) {
                       newAmt = newAmt * (1 - Double.parseDouble(discount.getText().toString()) / 100);
                   }

                   totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                   int numPerson = Integer.parseInt(numPax.getText().toString());
                   if (numPerson != 1) {
                       int checkedRadioId = paymentMethod.getCheckedRadioButtonId();
                       if (checkedRadioId == R.id.radioButtonCash) {
                           eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + " in cash.");
                       } else {
                           eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + " via Paynow to 912345678.");
                       }

                   } else {
                       eachPays.setText("Each Pays: $" + newAmt);

                   }
               } else {
                   totalBill.setText("Please input the amount and number of pax!");
               }
           }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");numPax.setText("");
                svc.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });

    }

}