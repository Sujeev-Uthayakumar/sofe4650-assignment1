package com.sujeevuthayakumar.sofe4650_assignment1;

import androidx.annotation.NonNull;
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

        double principalAmount = getIntent().getDoubleExtra("principalAmount", 0);
        double interestRate = getIntent().getDoubleExtra("interestRate", 0);
        double amortizationPeriod = getIntent().getDoubleExtra("amortizationPeriod", 0);

        double monthlyPayment = calculateEMI(principalAmount, interestRate, amortizationPeriod);

        TextView principalTextView = findViewById(R.id.principal_amount_text);
        principalTextView.setText("Principal Amount: $" + principalAmount);

        TextView interestRateTextView = findViewById(R.id.interest_rate_text);
        interestRateTextView.setText("Interest Rate: " + interestRate + "%");

        TextView amortizationTextView = findViewById(R.id.amortization_period_text);
        amortizationTextView.setText("Amortization Period: " + amortizationPeriod + " years");

        TextView monthlyPaymentTextView = findViewById(R.id.monthly_payment);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedAmount = decimalFormat.format(monthlyPayment);
        monthlyPaymentTextView.setText("Monthly Payment: $" + formattedAmount);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static double calculateEMI(double principal, double interestRate, double amortizationPeriod) {
        double monthlyInterestRate = (interestRate / 12) / 100;
        double loanTenureMonths = amortizationPeriod * 12;

        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTenureMonths))
                / (Math.pow(1 + monthlyInterestRate, loanTenureMonths) - 1);
        return emi;
    }
}