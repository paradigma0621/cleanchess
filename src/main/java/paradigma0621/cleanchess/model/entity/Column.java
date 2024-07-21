package paradigma0621.cleanchess.model.entity;

//import groupId.artifactId.
@SuppressWarnings("all")
public class Column { // extends Application {
	// private Casa casas[64];
    //	private Square sq;
	private int lineInt, columnInt;
	private String lineStr, columnStr;
    
	public Column() {
	}

	public int getColumnInt() {
		return columnInt;
	}

	public void setColumnInt(int columnInt) {

		this.columnInt = columnInt;

		char c = (char) (columnInt + 96);
		this.columnStr = String.valueOf(c);
	}

	public String getColumnStr() {
		return columnStr;

	}

	public void setColumnStr(String columnStr) {
		this.columnStr = columnStr;
		columnInt = Integer.valueOf(columnStr.charAt(0)-96);
	}

}
