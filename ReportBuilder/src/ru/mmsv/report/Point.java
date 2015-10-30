package ru.mmsv.report;

public class Point {
	
	private int thresHoldcolumn;
	
	private int thresHoldrow;
	
	private int startColumn;
	
	private int startRow;
	
	public Point(int column, int row) {
		super();
		this.thresHoldcolumn = column;
		this.thresHoldrow = row;
	}

	public int getColumn() {
		return thresHoldcolumn;
	}

	public int getRow() {
		return thresHoldrow;
	}
	
	public int getStartColumn() {
		return startColumn;
	}

	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

}
