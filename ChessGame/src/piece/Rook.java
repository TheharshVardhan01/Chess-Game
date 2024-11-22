package piece;

import Main.GamePanel;
import Main.Type;

public class Rook extends Piece{

	public Rook(int color, int col, int row) {
		super(color, col, row);
		// TODO Auto-generated constructor stub
		type=Type.ROOK;
		if(color == GamePanel.WHITE)
		{
			image = getImage("/piece/w-rook");
		}
		else
		{
			image = getImage("/piece/b-rook");
		}
	}
	
	public boolean canMove(int targetCol,int targetRow)
	{
		if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow)== false)
		{
			// Rook can move as long as either its col or row is the same
			if(targetCol==preCol || targetRow==preRow)
			{
				if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false) {
				return true;
				}
			}
		}
		
	
	return false;
	
   }
}