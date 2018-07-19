package com.help;

public class CurrentTimeHelp {
	public String getcurrentTime(String currentTime) {
		double theTime_1 = Double.parseDouble(currentTime); // Ãë
		int theTime = (int) theTime_1;
		int theTime1 = 0; // ·Ö
		int theTime2 = 0; // Ð¡Ê±
		// alert(theTime);
		if (theTime > 60) {
			theTime1 = theTime / 60;
			theTime = theTime % 60;
			// alert(theTime1+"-"+theTime);
			if (theTime1 > 60) {
				theTime2 = theTime1 / 60;
				theTime1 = theTime1 % 60;
			}
		}
		
		String result = null;
		if (theTime2 > 0) {
			if (theTime1 > 0 && theTime1 < 10) {
				if (theTime > 10) {
					result = theTime2 + ":" + "0" + theTime1 + ":" + theTime;
				} else {
					result = theTime2 + ":" + "0" + theTime1 + ":" + "0"
							+ theTime;
				}
			} else {
				if (theTime > 10) {
					result = theTime2 + ":" + theTime1 + ":" + theTime;
				} else {
					result = theTime2 + ":" + theTime1 + ":" + "0" + theTime;
				}
			}
		} else {
			if (theTime1 > 0 && theTime1 < 10) {
				if (theTime > 10) {
					result = "00" + ":" + "0" + theTime1 + ":" + theTime;
				} else {
					result = "00" + ":" + "0" + theTime1 + ":" + "0" + theTime;
				}
			} else {
				if (theTime > 10) {
					result = "00" + ":" + theTime1 + ":" + theTime;
				} else {
					result = "00" + ":" + theTime1 + ":" + "0" + theTime;
				}
			}
		}

		return result;
	}
}
