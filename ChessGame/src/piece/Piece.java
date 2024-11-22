package piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Board;
import Main.GamePanel;
import Main.Type;

public class Piece {
	
	public Type type;
	public BufferedImage image;
	public int x,y;
	public int col,row,preCol,preRow;
	public int color;
	public Piece hittingP;
	
	public boolean moved,twoStepped;
	
	public Piece(int color,int col,int row)
	{
		this.color = color;
		this.col = col;
		this.row = row;
		x = getX(col); // this types of methods are pretty handy as we can easily gets it x or y by knowing which column and row it is present
		y = getY(row); 
		preCol = col;
		preRow = row;
	}
	// To used the images of the piece in the program we have to import them as a buffered image thats what we are doing here
	public BufferedImage getImage(String imagePath)
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(getClass().getResource(imagePath+".png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	public int getX(int col)
	{
		return col*Board.SQUARE_SIZE;
	}
	public int getY(int row)
	{
		return row*Board.SQUARE_SIZE;
	}
	public int getCol(int x)
	{
		return(x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
	}
	public int getRow(int y)
	{
		return(y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;	
	}
	public int getIndex()   
	{
		for(int index = 0; index< GamePanel.simPieces.size(); index++)
		{
			//to get index of the our piece on which it is hovered or present
			if(GamePanel.simPieces.get(index) == this)
			{
				return index;
			}
		}
		return 0;
	}
	
	public void updatePostion()
	{
		// To check En Passant
		if(type ==Type.PAWN)
		{
			if(Math.abs(row-preRow)==2)
			{
			twoStepped=true;
			}
		}
		x = getX(col);
		y = getY(row);
		preCol = getCol(x);
		preRow = getRow(y);
		moved=true;
	}
	public void resetPosition()    //when actual move does not match the set move
	{
		col=preCol;
		row=preRow;
		x= getX(col);
		y= getY(row);
		moved=true;
		
	}
	public boolean canMove(int targetCol, int targetRow)    //will be overrided in each piece's class
	{
		return false;
	}
	public boolean isWithinBoard(int targetCol, int targetRow)
	{
		if(targetCol>=0 && targetCol<=7 && targetRow>=0 && targetRow <=7)
		{
			return true;
		}
		return false;
	}
	public boolean isSameSquare(int targetCol,int targetRow)
	{
		if(targetCol==preCol && targetRow==preRow)
		{
			return true;
		}
		return false;
	}
	public Piece getHittingP(int targetCol, int targetRow)   
	{
		for(Piece piece: GamePanel.simPieces)
		{
			//if the target hitting piece if if the same color
			if(piece.col == targetCol && piece.row== targetRow && piece!=this)
			{
				return piece;
			}
		}
		return null;
	}
	public boolean isValidSquare(int targetCol, int targetRow)
	{
		hittingP= getHittingP(targetCol,targetRow);
		
		if(hittingP == null)  //this square has no piece
		{
			return true;
		}
		else //this square is occupied
		{
			if(hittingP.color != this.color)  //if the color is different, it can be captured
			{
				return true;
			}
			else 
			{
				hittingP = null;
			}
			
		}
		return false;
	}
	public boolean pieceIsOnStraightLine(int targetCol,int targetRow)
	{
		// When this piece is moving to the left
		for(int c=preCol-1;c>targetCol;c--)
		{
			for(Piece piece:GamePanel.simPieces)
			{
				if(piece.col==c && piece.row ==targetRow)
				{
					hittingP=piece;
					return true;
				}
			}
		}
		// When this piece is moving to the right
		for(int c=preCol+1;c<targetCol;c++)
		{
			for(Piece piece:GamePanel.simPieces)
			{
				if(piece.col==c && piece.row ==targetRow)
				{
					hittingP=piece;
					return true;
				}
			}
		}
		// When this piece is moving up
		for(int r=preRow-1;r>targetRow;r--)
		{
			for(Piece piece:GamePanel.simPieces)
			{
				if(piece.col==targetCol && piece.row == r)
				{
					hittingP=piece;
					return true;
				}
			}
		}
		// When this piece  is moving down
		for(int r=preRow+1;r<targetRow;r++)
		{
			for(Piece piece:GamePanel.simPieces)
			{
				if(piece.col==targetCol && piece.row == r)
				{
					hittingP=piece;
					return true;
				}
			}
		}
		
	return false;
		
	}
	public boolean pieceIsOnDiagonalLine(int targetCol,int targetRow)
	{
		if(targetRow < preRow)
			
		//Up  left
			for(int c=preCol-1;c>targetCol;c--)
			{
				int diff=Math.abs(c-preCol);
				for(Piece piece:GamePanel.simPieces)
				{
					if(piece.col ==c && piece.row==preRow-diff)
					{
					hittingP=piece;
					return true;
					}
				}
			}
		// Up Right
		for(int c=preCol+1;c<targetCol;c++)
		{
			int diff=Math.abs(c-preCol);
			for(Piece piece:GamePanel.simPieces)
			{
				if(piece.col ==c && piece.row==preRow-diff)
				{
				
				hittingP=piece;
				return true;
				}
			}
		}
		if(targetRow > preRow)
		{
			// Down left
			for(int c=preCol-1;c>targetCol;c--)
			{
				int diff=Math.abs(c-preCol);
				for(Piece piece:GamePanel.simPieces)
				{
					if(piece.col ==c && piece.row==preRow+diff)
					{	
					hittingP=piece;
					return true;
					}
				}
			}
			// Down right
			for(int c=preCol+1;c<targetCol;c++)
			{
				int diff=Math.abs(c-preCol);
				for(Piece piece:GamePanel.simPieces)
				{
					if(piece.col ==c && piece.row==preRow+diff)
					{	
					hittingP=piece;
					return true;
					}
				}
			}
			
		}
		
		
		return false;
	}
	public void draw(Graphics2D g2)
	{
		g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
	}
	
}
