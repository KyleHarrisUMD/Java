package model;
import java.util.Random;

/* This class must extend Game */
<<<<<<< ClearCellGame.java
public class ClearCellGame extends Game {

	public ClearCellGame(int maxRows, int maxCols) {
		super(maxRows, maxCols);
		// TODO Auto-generated constructor stub
	}
=======
public class ClearCellGame extends Game {



	/**
	 * Constructor which takes two parameters, the maximum rows and the maximum columns.
	 * Also defines an empty board with empty cells. 
	 * 
	 * @param int maxRows - maximum rows entered by the user
	 * @param int maxCols - maximum columns entered by the user
	 */
	private int score;
	private java.util.Random randomObj;
	private int strategy;

	private int writtenRows[];
	private int stepCounter;

	public ClearCellGame(int maxRows, int maxCols){
		// delegate to superclass constructor
		super(maxRows, maxCols);
		writtenRows = new int[maxRows];
		stepCounter = 0;
	}

	public ClearCellGame(int maxRows, int maxCols, java.util.Random random, int strategy){
		super(maxRows, maxCols);
		this.randomObj = random;
		this.strategy = strategy;

	}

	/*The game is over when the last board row (row with index board.length -1)
	 * is different from empty row.
	 */
	@Override
	public boolean isGameOver() {

		// lets be optimistic at first
		boolean gameOver = false;
		int lastRow = this.board.length-1;
		// loop with respect to x while y in last row
		for(int x = 0; x<this.getMaxCols(); x++){
			// this.getBoardCell already has this.isValidIndex
			BoardCell currentCell = this.getBoardCell(lastRow, x);
			if(currentCell!=BoardCell.EMPTY){
				// look how far optimism got us. 
				gameOver = true;
			}
		}
		return gameOver;
	}

	@Override
	public int getScore() {
		return this.score;
	}


	/**
	 * This method will attempt to insert a row of random
	 *  BoardCell objects if the last board row (row with index board.length -1)
	 *  corresponds to the empty row; otherwise no operation will take place.
	 */
	@Override
	public void nextAnimationStep() {
		//this.stepCounter++;
		// check if the game isn't over 
		if(!isGameOver()){
			if(this.isEmptyRow(this.getMaxRows()-1)){


				pushDown();
				//System.out.println("Game isn't over");
				// create a new row of random BoardCell elements 
				BoardCell [] insertElements = new BoardCell[this.getMaxCols()]; 
				// for the lenght of the new array, generate a random element
				for(int x = 0; x<insertElements.length; x++){
					insertElements[x] = BoardCell.getNonEmptyRandomBoardCell(randomObj);
					//	System.out.println("new cell created : " + insertElements[x].getName());
				}

				// loop to find the next empty row.. when it is found
				// when found add the randomly generated objects there
				int insertRowIndex = nextEmptyRow();
				for(int x = 0 ; x<this.getMaxCols(); x++){
					setBoardCell(insertRowIndex, x, insertElements[x]);
					//  System.out.println("Set board elem");
				}



				// print out the text representation 
				System.out.println(getBoardStr(this));
				System.out.println("-------------------");

			}
		}
	}

	private void pushDown(){
		// method is a helper to push down contents and make a new row.. 
		if(this.isEmptyRow(0)){
			return;
		}

		int numRowsWithElems = 0; 
		for(int x =0; x<this.getMaxRows(); x++){
			if(!this.isEmptyRow(x)){
				numRowsWithElems++;
			}
		}

		BoardCell [][] tempElements = new BoardCell[numRowsWithElems][this.getMaxCols()];
		for(int x = 0; x<numRowsWithElems; x++){
			int tempCol = 0;
			while(tempCol<this.getMaxCols()){
				tempElements[x][tempCol] = super.getBoardCell(x, tempCol);
				tempCol++;
			}
		}

		// now we want to clear the entire board... 
		super.setBoardWithColor(BoardCell.EMPTY);
		// now replace the next elements with the temp elements with will basically push them down
		for(int y  = 0 ; y<tempElements.length; y++){
			for(int x = 0; x<this.maxCols; x++){
				board[y+1][x] = tempElements[y][x];
			}
		}
	}

	private int nextEmptyRow(){
		boolean emptyFound = false;
		int rowIndex = 0; 

		while(emptyFound==false && rowIndex < this.maxRows){
			if(isEmptyRow(rowIndex)==true){
				emptyFound=true;
			}else{
				rowIndex++;
			}
		}
		return rowIndex;

	}




	private boolean isEmptyRow(int rowIndex){
		int colIndex = 0; 
		boolean emptyRow = true;
		for(int x = 0; x<this.maxCols; x++){
			if(this.board[rowIndex][x] != BoardCell.EMPTY){
				emptyRow = false;
			}
		}
		return emptyRow;
	}



	@Override
	public void processCell(int rowIndex, int colIndex) {


		System.out.println("Process cell called at  : " + rowIndex+ ", "+colIndex);
		// check if the cell is empty
		if(rowIndex<0 || colIndex<0){
			return;
		}

		if(board[rowIndex][colIndex] == BoardCell.EMPTY){
			return;
		}

		/* kinda want to check if cells are the same based 
		 * on a string representation .. 
		 *
		 */

		// the enum type is going to return exactly what we want so lets use that.
		String stringRep = board[rowIndex][colIndex].toString();
		
		if(board[rowIndex][colIndex]!=BoardCell.EMPTY){
			super.setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
			this.score++;
		}


		/*
		 *
		 * There are some basic cases we need to consider 
		 * 
		 *  The situations are as follows..
		 *   Forward nodes & backward nodes, adjacent nodes and diagonal nodes.
		 *  
		 * The adjacent cells can be covered in 4 cases while the remaining forward 
		 * and backward need two other cases. In this particular situation, we can wrap
		 * the the forward and backwards check into part of the adjacent check.. 
		 */

		// lets start by testing just the adjacent cell processing

		/* directionally, here is the adjacent layout
		 * 
		 *  immediate right : colIndex+1, rowIndex
		 *  immediate left  : colIndex-1, rowIndex
		 *  
		 *  top    : colIndex, rowIndex-1;
		 *  bottom : colIndex, rowIndex+1;
		 *  
		 */

		// there is not really a better way to check this than by doing if statements.. 


		// before trying to process a cell, check if it is valid..
		// checking immediate left
		// oh yea, should also clear its self


		/*
		 * this block of code is going to cover clearing adjacent elements.. 
		 * as far as I can deduce, the cells are cleared until a faulty element
		 * is found.. so really just do a while loop for each direction to check
		 */

		/* 
		 * Code clears all elements that are considered forward of the target element.
		 */

		int forwardIndex = rowIndex-1; // Initially the forward index will be same column, 1 row ahead 
		boolean forwardStop = true; // used to see if we've encountered something that isn't of the same type.
		while(forwardIndex >= 0 && forwardStop){ // this way it will never step out of bounds and stop when its supposed to 
			String tempForwardString; 
			if(isValidIndex(forwardIndex, colIndex)){
				tempForwardString = board[forwardIndex][colIndex].toString();
				// check if it matches // 
				if(stringRep.equalsIgnoreCase(tempForwardString)){
					// clear the cell and increment score. 
					board[forwardIndex][colIndex] = BoardCell.EMPTY;
					forwardIndex--;
					this.score++;
				}else{ // its a different type of cell
					forwardStop = false;
				}
			}

		}


		int backwardIndex = rowIndex+1; // Initially the forward index will be same column, 1 row ahead 
		boolean backwardStop = true; // used to see if we've encountered something that isn't of the same type.
		while(backwardIndex < this.maxRows && backwardStop){ // this way it will never step out of bounds and stop when its supposed to 
			String tempBackwardString; 
			if(isValidIndex(backwardIndex, colIndex)){
				tempBackwardString = board[backwardIndex][colIndex].toString();
				// check if it matches // 
				if(stringRep.equalsIgnoreCase(tempBackwardString)){
					// clear the cell and increment score. 
					board[backwardIndex][colIndex] = BoardCell.EMPTY;
					backwardIndex++;
					this.score++;
				}else{ // its a different type of cell
					backwardStop = false;

				}
			}

		}



		/* Two blocks of code below cover the clearing of left and right adjacent elements */ 

		int leftwardIndex = colIndex-1; // Initially the forward index will be same column, 1 row ahead 
		boolean leftwardStop = true; // used to see if we've encountered something that isn't of the same type.
		while(leftwardIndex >=0 && leftwardStop){ // this way it will never step out of bounds and stop when its supposed to 
			String tempLeftwardString; 
			if(isValidIndex(rowIndex, leftwardIndex)){
				tempLeftwardString = board[rowIndex][leftwardIndex].toString();
				// check if it matches // 
				if(stringRep.equalsIgnoreCase(tempLeftwardString)){
					// clear the cell and increment score. 
					board[rowIndex][leftwardIndex] = BoardCell.EMPTY;
					leftwardIndex--;
					this.score++;
				}else{ // its a different type of cell
					leftwardStop = false;

				}
			}

		}

		int rightwardIndex = colIndex+1; // Initially the forward index will be same column, 1 row ahead 
		boolean rightwardStop = true; // used to see if we've encountered something that isn't of the same type.
		while(rightwardIndex < this.maxCols && rightwardStop){ // this way it will never step out of bounds and stop when its supposed to 
			System.out.println("Infinatley looping");
			String tempRightwardString; 
			if(isValidIndex(rowIndex, rightwardIndex)){
				tempRightwardString = board[rowIndex][rightwardIndex].toString();
				// check if it matches // 
				if(stringRep.equalsIgnoreCase(tempRightwardString)){
					// clear the cell and increment score. 
					board[rowIndex][rightwardIndex] = BoardCell.EMPTY;
					rightwardIndex++;
					this.score++;
				}else{ // its a different type of cell
					rightwardStop = false;
					break;
				}
			}{
				rightwardStop = true;	
			}

		}

		/*
		 * Now we have to do the same thing diagonally 
		 * 
		 * It won't be too hard. 
		 * For a diagonal cell in the leftwards and up case it is as follows 
		 * 
		 * diagUpLeft = row-x,    col-x
		 * diagDownLeft = row+x , col-x
		 * 
		 * 
		 * diagUpright = row-x, col+x
		 * diagDownRight = row+x, col+x 
		 * 
		 * see while loops below 
		 */

		int upLeft_left = colIndex-1;
		int upLeft_up   = rowIndex-1 ;
		boolean leftUpStop = true;
		while((upLeft_left >=0 && upLeft_up>=0 ) && leftUpStop){

			String tempUpLeft; 
			if(isValidIndex(upLeft_up, upLeft_left)){
				tempUpLeft = board[upLeft_up][upLeft_left].toString();
				if(stringRep.equals(tempUpLeft)){
					board[upLeft_up][upLeft_left]=BoardCell.EMPTY; 
					this.score++;
					// now decrement both
					upLeft_left--;
					upLeft_up--;
				}else{
					leftUpStop = false;
				}
			}else{
				leftUpStop = false;
			}
		}


		// this goes down and to the left

		int downLeft_left = colIndex-1;
		int downLeft_down   = rowIndex+1 ;
		boolean leftDownStop = true;


		while((downLeft_left >=0 && downLeft_down<this.maxRows ) && leftDownStop){

			String tempDownLeft; 
			if(isValidIndex(downLeft_down, downLeft_left)){
				tempDownLeft = board[downLeft_down][downLeft_left].toString();
				if(stringRep.equals(tempDownLeft)){
					board[downLeft_down][downLeft_left]=BoardCell.EMPTY; 
					this.score++;
					// now decrement both
					downLeft_left--;
					downLeft_down++;
				}else{
					leftUpStop = false;
					break;
				}
			}else{
				leftDownStop = false;
				break;
			}

		}



		/**
		 * Clearing diagonal cells works to the left to the left so just now wiggie with that big attitude to the right
		 */
		int upRight_right = colIndex+1;
		int upRight_up    = rowIndex-1;
		boolean rightUpStop = true;
		while((upRight_right < this.maxCols && upRight_up >=0 ) && rightUpStop){

			String tempUpRight; 
			if(isValidIndex(upRight_up, upRight_right)){
				tempUpRight = board[upRight_up][upRight_right].toString();
				if(stringRep.equals(tempUpRight)){
					board[upRight_up][upRight_right]=BoardCell.EMPTY; 
					this.score++;
					// now decrement both
					upRight_right++;
					upRight_up--;
				}else{
					rightUpStop = false;
					break;
				}
			}else{
				rightUpStop = false;
				break;
			}
		}


		/**
		 * Clearing diagonal cells works to the left to the left so just now wiggie with that big attitude to the right
		 */
		int downRight_right = colIndex+1;
		int downRight_down    = rowIndex+1;
		boolean rightDownStop = true;
		while((downRight_right < this.maxCols && downRight_down >=0 ) && rightDownStop){

			String tempdownRight; 
			if(isValidIndex(downRight_down, downRight_right)){
				tempdownRight = board[downRight_down][downRight_right].toString();
				if(stringRep.equals(tempdownRight)){
					board[downRight_down][downRight_right]=BoardCell.EMPTY; 
					this.score++;
					// now decrement both
					downRight_right++;
					downRight_down++;
				}else{
					rightDownStop = false;
					break;
				}
			}else{
				rightDownStop = false;
				break;
			}
		}




		// see if we need to collapse the cells //
		for(int y = 0; y<this.maxRows; y++){
			boolean collapse = false;
			int replaceRow = 0;
			boolean rowFlag = true;

			int tempRow = this.nextEmptyRow();
			while(tempRow < this.getMaxRows() && rowFlag){
				int tempCol = 0;
				while(tempCol < this.getMaxCols()){
					if(board[tempRow][tempCol]!=BoardCell.EMPTY){
						collapse=true;
						replaceRow = tempRow;
						rowFlag = false;
					}
					tempCol++;
				}
				tempRow++;
			}

			if(collapse){
				System.out.println("Collapse  = true; rows : "+this.nextEmptyRow() + "replace  : "+replaceRow);
				int empty = this.nextEmptyRow();
				for(int x = 0; x<this.getMaxCols(); x++){
					System.out.println("Should be replacing");
					board[empty][x] = board[replaceRow][x];
					board[replaceRow][x] = BoardCell.EMPTY;
				}
			}
		}
	}


	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}

>>>>>>> 1.6

}