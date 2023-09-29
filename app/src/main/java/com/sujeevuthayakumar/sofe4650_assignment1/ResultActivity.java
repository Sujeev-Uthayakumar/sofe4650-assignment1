package com.sujeevuthayakumar.sofe4650_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the data that is passed in from the SecondFragment
        double principalAmount = getIntent().getDoubleExtra("principalAmount", 0);
        double interestRate = getIntent().getDoubleExtra("interestRate", 0);
        double amortizationPeriod = getIntent().getDoubleExtra("amortizationPeriod", 0);

        // Calculate the EMI using the values above
        double monthlyPayment = calculateEMI(principalAmount, interestRate, amortizationPeriod);

        // Set the text for principal amount
        TextView principalTextView = findViewById(R.id.principal_amount_text);
        principalTextView.setText("Principal Amount: $" + principalAmount);

        // Set the text for interest rate
        TextView interestRateTextView = findViewById(R.id.interest_rate_text);
        interestRateTextView.setText("Interest Rate: " + interestRate + "%");

        // Set the text for amortization
        TextView amortizationTextView = findViewById(R.id.amortization_period_text);
        amortizationTextView.setText("Amortization Period: " + amortizationPeriod + " years");

        // Set the text for the monthly payment
        TextView monthlyPaymentTextView = findViewById(R.id.monthly_payment);

        // Format code to represent money
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedAmount = decimalFormat.format(monthlyPayment);
        monthlyPaymentTextView.setText("Monthly Payment: $" + formattedAmount);

        // The onClick when the back button is pressed

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This will navigate the user back to the MainActivity
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Function to calculate the EMI
    public static double calculateEMI(double principal, double interestRate, double amortizationPeriod) {
        // Convert values to account for months instead of years
        double monthlyInterestRate = (interestRate / 12) / 100;
        double loanTenureMonths = amortizationPeriod * 12;

        // Return the EMI amount
        return (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTenureMonths))
                / (Math.pow(1 + monthlyInterestRate, loanTenureMonths) - 1);
    }
}