package com.example.aatg.tc;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditNumber extends EditText {

	public static final String DEFAULT_FORMAT = "%.2f";

	public EditNumber(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public EditNumber(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public EditNumber(Context context) {
		super(context);
		init();
	}

	private void init(){
		InputFilter[] filters = new InputFilter[]{DigitsKeyListener.getInstance(true, true)};
		setFilters(filters);
	}
	public void clear() {
		setText("");
	}

	public void setNumber(double f) {
		setText(String.format(DEFAULT_FORMAT,f));
	}

	public double getNumber() {
		return Double.parseDouble(getText().toString());
		//return Double.valueOf(getText().toString());
	}
	

}
