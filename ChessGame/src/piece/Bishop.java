package piece;

import Main.GamePanel;
import Main.Type;

public class Bishop extends Piece{

	public Bishop(int color, int col, int row) {
		super(color, col, row);
		type=Type.BISHOP;
		// TODO Auto-generated constructor stub
		if(color == GamePanel.WHITE)
		{
			image = getImage("/piece/w-bishop");
		}
		else
		{
			image = getImage("/piece/b-bishop");
		}
	}
	public boolean canMove(int targetCol,int targetRow)
	{
		if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow)== false)
		{
			// Bishop can move as long as in its diagonals
			if(Math.abs(targetCol-preCol) ==Math.abs(targetRow-preRow))
			{
				if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false) {
				return true;
				}
			}
		}
		
	
	return false;
	
   }
}
