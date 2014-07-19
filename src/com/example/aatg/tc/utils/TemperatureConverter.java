package com.example.aatg.tc.utils;

import com.example.aatg.tc.exception.InvalidTemperatureException;

public class TemperatureConverter {

	public static double ABSOLUTE_ZERO_C = -273.15d;
	public static double ABSOLUTE_ZERO_F = -459.67d;
	public static String ERROR_MESSAGE_BELOW_ZERO_FMT = "Invalid temperature:%.2f%s below absolute zero";
	public static double fahrenheitToCelsius(double f) {
		if(f<ABSOLUTE_ZERO_F){
			throw new InvalidTemperatureException(String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, f,"F"));
		}
		return ((f-32)/1.8d);
	}
	public static double celsiusToFahrenheit(double c) {
		if(c<ABSOLUTE_ZERO_C){
			throw new InvalidTemperatureException(String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, c,"C"));
		}
		return (c*1.8d+32);
	}

}
