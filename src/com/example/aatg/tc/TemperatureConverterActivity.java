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

	// �ڲ���
	public class TemperatureChangedWatcher implements TextWatcher {

		private EditNumber mSource;
		private EditNumber mDest;
		private OP mOp;

		public TemperatureChangedWatcher(OP op) {
			// ����opֵ�Ĳ�ͬ,��Ӧ�ڲ�ͬ��source , dest
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
			// ���destĿ��������Ѿ�focus,�򷵻�
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
				// ���ݲ�ͬOP,ѡ��ͬ��ת������
				double result = (mOp == OP.C2F) ? TemperatureConverter
						.celsiusToFahrenheit(temp) : TemperatureConverter
						.fahrenheitToCelsius(temp);
				String resultString = String.format(EditNumber.DEFAULT_FORMAT,
						result);
				mDest.setNumber(result);
				mDest.setSelection(resultString.length());// ������ƶ�������
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
