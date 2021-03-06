package pl.edu.pwr.lab1.i238162;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private IBmiCalculator calculator = null;
    private double bmiValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateCalculator(new MetricBmiCalculator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        menu.setGroupDividerEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.metric_menu_item) {
            updateCalculator(new MetricBmiCalculator());
            item.setChecked(true);
            return true;
        } else if (id == R.id.imperial_menu_item) {
            updateCalculator(new ImperialBmiCalculator());
            item.setChecked(true);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateCalculator(IBmiCalculator newCalculator) {
        calculator = newCalculator;

        TextView massText = findViewById(R.id.massText);
        massText.setText(getString(R.string.mass, calculator.getMassUnit(this)));

        TextView heightText = findViewById(R.id.heightText);
        heightText.setText(getString(R.string.height, calculator.getHeightUnit(this)));
    }

    public void showCreditsDialog(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.credits_content)
                .setTitle(R.string.credits_title)
                .setPositiveButton(R.string.dialog_button_ok, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void doCalculation(View v) {
        CalculateButtonAction buttonAction = new CalculateButtonAction();
        buttonAction.onClick(v);
    }

    private class CalculateButtonAction implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideKeyboard();
            double mass = getDoubleFromInputField(R.id.massInputField);
            double height = getDoubleFromInputField(R.id.heightInputField);
            bmiValue = calculator.calculate(mass, height);
            TextView bmiOutput = findViewById(R.id.bmiValueText);
            bmiOutput.setText(String.format(Locale.getDefault(), "%.1f", bmiValue));
            BmiCategory category = BmiCategory.valueOfBmi(bmiValue);
            bmiOutput.setBackgroundColor(category.getColour(MainActivity.this));
        }

        private double getDoubleFromInputField(int inputFieldId) {
            EditText inputField = findViewById(inputFieldId);
            String input = inputField.getText().toString();
            if (input.isEmpty()) {
                return 0.0;
            } else {
                return Double.parseDouble(input);
            }
        }

        // stolen shamelessly from https://stackoverflow.com/a/17789187
        private void hideKeyboard() {
            InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = MainActivity.this.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(MainActivity.this);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showBmiExplanation(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.bmi_explanation_dialog_layout, null))
                .setPositiveButton(R.string.dialog_button_ok, null);

        AlertDialog dialog = builder.create();
        dialog.show();
        updateBmiExplanationValues(dialog);
    }

    private void updateBmiExplanationValues(Dialog d) {
        TextView bmiValueText = d.findViewById(R.id.bmi_explanation_value_textview);
        bmiValueText.setText(String.format(Locale.getDefault(), "%.1f", bmiValue));

        BmiCategory category = BmiCategory.valueOfBmi(bmiValue);
        TextView bmiCategoryText = d.findViewById(R.id.bmi_explanation_description_textview);
        bmiCategoryText.setText(category.getLabel(this));

        if (category == BmiCategory.NORMAL) {
            TextView congratsText = d.findViewById(R.id.bmi_explanation_congrats_textview);
            congratsText.setVisibility(View.VISIBLE);
        }
    }
}
