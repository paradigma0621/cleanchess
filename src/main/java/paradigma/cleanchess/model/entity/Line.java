package paradigma.cleanchess.model.entity;

import static java.lang.Character.getNumericValue;

public class Line {
	private int lineInt;
	private String lineStr;
   
	public Line() {
	}

	public int getLineInt() {
		return lineInt;
	}

	public void setLineInt(int lineInt) {
		this.lineInt = lineInt;
		char c = getCharacterValue(lineInt);
		this.lineStr = String.valueOf(c);
	}

	private char getCharacterValue(int lineInt) {
		return (char) (lineInt + 48);
	}

	public String getLineStr() {
		return lineStr;
	}

	public void setLineStr(String lineStr) {
		this.lineStr = lineStr;
		
		char c = lineStr.charAt(0);
		this.lineInt = getNumericValue(c);
	}
}
