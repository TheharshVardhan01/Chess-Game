package piece;

import Main.GamePanel;
import Main.Type;

public class Pawn extends Piece{

	public Pawn(int color, int col, int row) {
		super(color, col, row);
		// TODO Auto-generated constructor stub
		type=Type.PAWN;
		if(color == GamePanel.WHITE)
		{
			
			image = getImage("/piece/w-pawn");
		}
		else
		{
			image = getImage("/piece/b-pawn");
		}
	}
	public boolean canMove(int targetCol,int targetRow)
	{
		if(isWithinBoard(targetCol, targetRow) &&  isSameSquare(targetCol, targetRow)== false)
		{
		// Define the move value based on its color
		int moveValue;
		if(color== GamePanel.WHITE)
		{
			moveValue=-1;   //for white pawn
		}
		else
		{
			moveValue=1;   //for black pawn
		}
		// Check the hitting piece
		hittingP=getHittingP(targetCol, targetRow);
		// 1 square movement
		if(targetCol == preCol && targetRow== preRow+ moveValue && hittingP == null)
		{
			return true;
		}
		// 2 squares movement
		if(targetCol == preCol && targetRow== preRow+moveValue*2 && hittingP==null && moved==false && pieceIsOnStraightLine(targetCol, targetRow)== false)
		{
			return true;
		}
		// Diagonal  movement & Capture (if a piece is on a square diagonally in front of it )
		if(Math.abs(targetCol-preCol) == 1 && targetRow ==preRow +moveValue && hittingP!=null && hittingP.color!=color)
		{
			return true;
		}
		// En passant
		if(Math.abs(targetCol-preCol) == 1 && targetRow ==preRow +moveValue  )
		{
			for(Piece piece :GamePanel.simPieces)
			{
				if(piece.col ==targetCol && piece.row== preRow && piece.twoStepped ==true && piece.color!=color) {
					hittingP=piece;
					return true;
				
			}
			
		}
		
	}
		}
		
		
		return false;
	}
	

}
