package paradigma0621.cleanchess.model.entity;

public class Line {
	// private Casa casas[64];
    //  private Square sq;
	private int lineInt, columnInt;
	private String lineStr, columnStr;
   
	public Line() {
	}


	public int getLineInt() {
		return lineInt;
	}

	public void setLineInt(int lineInt) {
		this.lineInt = lineInt;
		char c = (char) (lineInt + 48);
		this.lineStr = String.valueOf(c);
	}

	public String getLineStr() {
		return lineStr;
	}

	public void setLineStr(String lineStr) {
		this.lineStr = lineStr;
		
		char c = lineStr.charAt(0);
		this.lineInt = ((int)(c)) - 48;
		System.out.println("line int setado para: "+String.valueOf(lineInt));
	}
}
