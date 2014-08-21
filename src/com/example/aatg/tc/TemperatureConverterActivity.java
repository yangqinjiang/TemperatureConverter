package com.example.aatg.tc;

import com.example.aatg.tc.utils.TemperatureConverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class TemperatureConverterActivity extends Activity {
	public enum OP {
		C2F, // celsiusToFahrenheit
		F2C// fahrenheitToCelsius
	}

	private EditNumber mCelsius;
	private EditNumber mFahrenheit;
	private TextView mCelsiusLabel;
	private TextView mFahrenheitLabel;

	// 内部类
	public class TemperatureChangedWatcher implements TextWatcher {

		private EditNumber mSource;
		private EditNumber mDest;
		private OP mOp;

		public TemperatureChangedWatcher(OP op) {
			// 根据op值的不同,对应于不同的source , dest
			if (op == OP.C2F) {
				this.mSource = mCelsius;
				this.mDest = mFahrenheit;
			} else {
				this.mSource = mFahrenheit;
				this.mDest = mCelsius;
			}
			this.mOp = op;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			// 如果dest目标输入框已经focus,则返回
			if (!mDest.hasWindowFocus() || mDest.hasFocus() || s == null) {
				return;
			}
			String str = s.toString();
			if ("".equals(str)) {
				mDest.setText("");
				return;
			}
			try {
				double temp = Double.parseDouble(str);
				// 根据不同OP,选择不同的转换函数
				double result = (mOp == OP.C2F) ? TemperatureConverter
						.celsiusToFahrenheit(temp) : TemperatureConverter
						.fahrenheitToCelsius(temp);
				String resultString = String.format(EditNumber.DEFAULT_FORMAT,
						result);
				mDest.setNumber(result);
				mDest.setSelection(resultString.length());// 将光标移动到后面
			} catch (NumberFormatException e) {
				// WARNING
				// this is generated while a number is entered,
				// for example just a '-'
				// so we don't want to show the error
				mSource.setError("ERROR: " + e.getLocalizedMessage());
			} catch (Exception e) {
				mSource.setError("ERROR: " + e.getLocalizedMessage());
			}

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mCelsius = (EditNumber) findViewById(com.example.aatg.tc.R.id.celsius);
		mFahrenheit = (EditNumber) findViewById(com.example.aatg.tc.R.id.fahrenheit);
		mCelsiusLabel = (TextView) findViewById(com.example.aatg.tc.R.id.celsius_label);
		mFahrenheitLabel = (TextView) findViewById(com.example.aatg.tc.R.id.fahrenheit_label);
		//
		mCelsius.addTextChangedListener(new TemperatureChangedWatcher(OP.C2F));
		mFahrenheit.addTextChangedListener(new TemperatureChangedWatcher(OP.F2C));
	}
}
