package paradigma0621.cleanchess.model.entity;

import paradigma0621.cleanchess.model.BoardModel;

public class Square {
	// private Casa casas[64];
	private BoardModel board;
	private String sqAlgebric;
	private int sqInt;
	private Line line;
	private Column column;
   	private ColorSquareEnum colorSq;
   
	public Square() {
		Line line = new Line();
		Column column = new Column();
	}
	
	public Square(String sqAlgebric) {
 		Line line = new Line();
		Column column = new Column();
		this.line = line;
		this.column = column;
		this.sqAlgebric = sqAlgebric;

		String columnStr = new String(sqAlgebric.substring(0, 1));// takes the first digit
		String lineStr = new String(sqAlgebric.substring(1, 2)); // takes the second digit

		/*
		char c1 = sqAlgebric.charAt(0);
		column.setColumnStr(String.valueOf(c1));
		  
		char c2 = sqAlgebric.charAt(1);
		line.setLineStr(String.valueOf(c2));
		*/
		
		column.setColumnStr(columnStr); 
		line.setLineStr(lineStr);
			  
	}
    
	public Square(int lin, int col) {
 		Line line = new Line();
		Column column = new Column();
		line.setLineInt(lin);
		column.setColumnInt(col);
		setColorSquare();
	}

	public Square(BoardModel board) {
		this.board = board;
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

	public void setSqInt(int sqInt) {
		this.sqInt = sqInt;

		String str = String.valueOf(sqInt);
		String columnStr = str.substring(0, 1); // takes the first digit
		String lineStr = str.substring(1, 2); // takes the second digit

		column.setColumnStr(columnStr);
		column.setColumnInt(Integer.valueOf(columnStr));
		line.setLineStr(lineStr);
		line.setLineInt(Integer.valueOf(lineStr));
		System.out.println("debug: columnStr: " + column.getColumnStr() + " - lineStr: " + line.getLineStr());
	}

	public int getSqInt() {
		return sqInt;
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

    public ColorSquareEnum getColorSquare() {
		return colorSq;
    }
}
