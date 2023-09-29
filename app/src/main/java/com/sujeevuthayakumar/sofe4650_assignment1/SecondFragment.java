package com.sujeevuthayakumar.sofe4650_assignment1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sujeevuthayakumar.sofe4650_assignment1.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // The button onClick for the calculate button
        binding.calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // If the inputs aren't empty, the following will run
                if (!areInputsEmpty()) {
                    // Get the value of inputs
                    String principalAmount = binding.principalAmount.getText().toString();
                    String interestRate = binding.interestRate.getText().toString();
                    String amortizationPeriod = binding.amortizationPeriod.getText().toString();

                    // Pass the data along to the ResultActivity
                    Intent intent = new Intent(getContext(), ResultActivity.class);
                    intent.putExtra("principalAmount", Double.parseDouble(principalAmount));
                    intent.putExtra("interestRate", Double.parseDouble(interestRate));
                    intent.putExtra("amortizationPeriod", Double.parseDouble(amortizationPeriod));

                    // Navigate to the next activity
                    startActivity(intent);
                }
                // Style inputs based on errors
                styleErrorInputs();
            }
        });
    }

    // Function to check whether inputs are empty
    private boolean areInputsEmpty() {
        // Get the values of inputs
        String principalAmount = binding.principalAmount.getText().toString();
        String interestRate = binding.interestRate.getText().toString();
        String amortizationPeriod = binding.amortizationPeriod.getText().toString();

        // Returns true if any of the inputs are empty
        return principalAmount.isEmpty() || interestRate.isEmpty() || amortizationPeriod.isEmpty();
    }

    // Function to style inputs based on whether they are required
    private void styleErrorInputs() {
        // Get the values of inputs
        String principalAmount = binding.principalAmount.getText().toString();
        String interestRate = binding.interestRate.getText().toString();
        String amortizationPeriod = binding.amortizationPeriod.getText().toString();

        // Color for input error
        int color = Color.rgb(255,0,0);
        int color2 = Color.parseColor("#FF11AA");

        // The states for an input when clicked, and hovered
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_focused},
                new int[] { android.R.attr.state_hovered},
                new int[] { android.R.attr.state_enabled},
                new int[] { }
        };

        // Set all possible colors
        int[] colors = new int[] {
                color,
                color,
                color,
                color2
        };

        // The list of colors used for the state
        ColorStateList myColorList = new ColorStateList(states, colors);

        // Check for principal amount input
        if (principalAmount.isEmpty()) {
            binding.principalAmountLayout.setError("Required");
        } else {
            binding.principalAmountLayout.setError(null);
        }

        // Check for interest rate input
        if (interestRate.isEmpty()) {
            binding.interestRateLayout.setError("Required");
        } else {
            binding.interestRateLayout.setError(null);
        }

        // Check for amortization period input
        if (amortizationPeriod.isEmpty()) {
            binding.amortizationPeriodLayout.setError("Required");
        } else {
            binding.amortizationPeriodLayout.setError(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}