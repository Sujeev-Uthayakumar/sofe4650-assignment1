package com.sujeevuthayakumar.sofe4650_assignment1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
        binding.calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!areInputsEmpty()) {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_thirdFragment);
                }
                styleErrorInputs();
            }
        });
    }

    private boolean areInputsEmpty() {
        String principalAmount = binding.principalAmount.getText().toString();
        String interestRate = binding.interestRate.getText().toString();
        String amortizationPeriod = binding.amortizationPeriod.getText().toString();

        return principalAmount.isEmpty() || interestRate.isEmpty() || amortizationPeriod.isEmpty();
    }

    private void styleErrorInputs() {
        String principalAmount = binding.principalAmount.getText().toString();
        String interestRate = binding.interestRate.getText().toString();
        String amortizationPeriod = binding.amortizationPeriod.getText().toString();

        int color = Color.rgb(255,0,0);

        int color2 = Color.parseColor("#FF11AA");

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_focused},
                new int[] { android.R.attr.state_hovered},
                new int[] { android.R.attr.state_enabled},
                new int[] { }
        };

        int[] colors = new int[] {
                color,
                color,
                color,
                color2
        };
        ColorStateList myColorList = new ColorStateList(states, colors);

        if (principalAmount.isEmpty()) {
            binding.principalAmountLayout.setError("Required");
        } else {
            binding.principalAmountLayout.setError(null);
        }

        if (interestRate.isEmpty()) {
            binding.interestRateLayout.setError("Required");
        } else {
            binding.interestRateLayout.setError(null);
        }

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