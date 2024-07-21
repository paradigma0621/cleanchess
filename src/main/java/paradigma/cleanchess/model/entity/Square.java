package paradigma.cleanchess.model.entity;

import paradigma.cleanchess.model.BoardModel;

public class Square {
	private BoardModel board;
	private String sqAlgebric;
	private int sqInt;
	private Line line;
	private Column column;
   	private ColorSquareEnum colorSq;
   
	public Square(String sqAlgebric) {
		this.column = new Column();
		this.sqAlgebric = sqAlgebric;

		String columnStr = sqAlgebric.substring(0, 1);
		String lineStr = sqAlgebric.substring(1, 2);

		column.setColumnStr(columnStr);
		line.setLineStr(lineStr);
	}

	public Square(BoardModel board) {
		this.board = board;
	}

	public void setSqInt(int sqInt) {
		this.sqInt = sqInt;

		String str = String.valueOf(sqInt);
		String columnStr = str.substring(0, 1); // takes the first digit
		String lineStr = str.substring(1, 2); // takes the second digit

		column.setColumnStr(columnStr);
		column.setColumnInt(Integer.parseInt(columnStr));
		line.setLineStr(lineStr);
		line.setLineInt(Integer.parseInt(lineStr));
		System.out.println("debug: columnStr: " + column.getColumnStr() + " - lineStr: " + line.getLineStr());
	}

	public void setColorSquare() {
		System.out.print("metodo setColorSquare sendo executado");
		if (((column.getColumnInt() + line.getLineInt()) % 2) == 0) {
			System.out.println("c: " + column + " l: " + line + " é par=preta");
			colorSq = ColorSquareEnum.BLACK_SQUARE;
		}

		if (((column.getColumnInt() + line.getLineInt()) % 2) == 1) {
			System.out.println("c: " + column + " l: " + line + " é ímpar=branca");
			colorSq = ColorSquareEnum.WHITE_SQUARE;
		}
	}

	public int getSqInt() {
		return sqInt;
	}

    public ColorSquareEnum getColorSquare() {
		return colorSq;
    }

	public Line getLine() {
		return line;
	}

	public Column getColumn() {
		return column;
	}

	public String getSqAlgebric() {
		return sqAlgebric;
	}
}
