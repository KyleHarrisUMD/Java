package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game {


	protected BoardCell[][] board;


	protected int maxRows;
	protected int maxCols;
	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	private int maxCols;
	private int maxRows;
	
	public Game(int maxRows, int maxCols) {
<<<<<<< Game.java
		this.maxCols = maxCols;
		this.maxRows = maxRows;
         this.board = new BoardCell[maxRows][maxCols];
         // init each cell with BoardCell.EMPTY
         
         // loop with respect to y
         for(int directionY = 0; directionY<maxRows; directionY++){
        	 // loop with respect to x 
        	 for(int directionX = 0; directionX < maxCols; directionX++){
        		 // set the cells to the correct value
        		this.board[directionY][directionX] = BoardCell.EMPTY; 
        	 }
         }
=======

		// init private attributes
		if(maxRows>=0 && this.maxCols>=0){
			this.maxRows = maxRows;
			this.maxCols = maxCols;
			this.board = new BoardCell[maxRows][maxCols];
			// makes new game with empty cells 

			// delegates to setBoardcell function
			for(int y = 0; y<maxRows; y++){
				for(int x = 0; x<maxCols; x++){
					// set board cell to empty within nested loop
					this.board[y][x] = BoardCell.EMPTY;
				}
			}
		}

>>>>>>> 1.7
	}



	public int getMaxRows() {
		return this.maxRows;
	}

	public int getMaxCols() {
		return this.maxCols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {

		if(boardCell!=null){
			boolean valid = false;
			// check if its valid.. 
			BoardCell [] validCells = BoardCell.values();
			for(int x = 0; x<validCells.length; x++){
				if(validCells[x].toString().equals(boardCell.toString())){
					valid = true;
				}
			}

			if(valid){
				if(isValidIndex(rowIndex,colIndex)){
					this.board[rowIndex][colIndex] = boardCell;  
				}else{
					System.out.println("INVALID INDEX");
				}
			}
		}

	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		// return empty so we don't run into a NPE
		BoardCell cell = BoardCell.EMPTY;
		if(isValidIndex(rowIndex, colIndex)){
			cell = this.board[rowIndex][colIndex];
		}
		return cell;
	}

	/**
	 * Initializes row with the specified color.
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {


		boolean valid = false;
		// check if its valid.. 
		BoardCell [] validCells = BoardCell.values();
		for(int x = 0; x<validCells.length; x++){
			if(validCells[x].toString().equals(cell.toString())){
				valid = true;
			}
		}
		if(valid){
			if(cell instanceof BoardCell){
				if(rowIndex>=0){
					// loop to set a specific row with a color
					int colIndex = 0;
					while(colIndex < this.getMaxCols()){
						board[rowIndex][colIndex] = cell;
						this.setBoardCell(rowIndex, colIndex, cell);
						colIndex++;
					}
				}
			}
		}
	}


	/**
	 * Initializes column with the specified color.
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		if(cell!=null){
			boolean valid = false;
			// check if its valid.. 
			BoardCell [] validCells = BoardCell.values();
			for(int x = 0; x<validCells.length; x++){
				if(validCells[x].toString() == cell.toString()){
					valid = true;
				}
			}
			if(valid){
				int rowIndex = 0;
				while(rowIndex < this.getMaxRows()){
					this.setBoardCell(rowIndex, colIndex, cell);
					rowIndex++;
				}
			}
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		if(cell!=null){
			boolean valid = false;
			// check if its valid.. 
			BoardCell [] validCells = BoardCell.values();
			for(int x = 0; x<validCells.length; x++){
				if(validCells[x].toString().equals(cell.toString())){
					valid = true;
				}
			}

			if(valid){
				// just loop with respect to x and do something cool 
				for(int x  =0; x<this.getMaxCols(); x++){
					// this will go vertical and set the desired column with the color
					this.setColWithColor(x, cell);
				}
			}
		}
	}	

	
	
	
	protected boolean isValidIndex(int rowIndex, int colIndex){
		if(rowIndex>=0&&colIndex>=0 && colIndex<this.maxCols && rowIndex<this.maxRows){
			return true;
		}else{
			return false;
		}

	}




	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the
	 * selected cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);


}