package pl.edu.pwr.lab1.i238162;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private IBmiCalculator calculator = null;

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
            double bmi = calculator.calculate(mass, height);
            TextView bmiOutput = findViewById(R.id.bmiValueText);
            bmiOutput.setText(String.valueOf(bmi));
        }

        private double getDoubleFromInputField(int inputFieldId) {
            EditText inputField = findViewById(inputFieldId);
            String input = inputField.getText().toString();
            if (input == null || input.isEmpty()) {
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
}
