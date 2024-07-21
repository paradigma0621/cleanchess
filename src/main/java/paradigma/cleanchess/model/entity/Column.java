package paradigma.cleanchess.model.entity;

@SuppressWarnings("all")
public class Column {
	private int columnInt;
	private String columnStr;
    
	public Column() {
	}

	public void setColumnInt(int columnInt) {
		this.columnInt = columnInt;

		char c = getCharacterFromInteger(columnInt);
		this.columnStr = String.valueOf(c);
	}

	public void setColumnStr(String columnStr) {
		this.columnStr = columnStr;
		columnInt = getIntegerFromCharacter(columnStr);
	}

	private int getIntegerFromCharacter(String columnStr) {
		return Integer.valueOf(columnStr.charAt(0) - 96);
	}

	private char getCharacterFromInteger(int columnInt) {
		return (char) (columnInt + 96);
	}

	public int getColumnInt() {
		return columnInt;
	}

	public String getColumnStr() {
		return columnStr;
	}

}
