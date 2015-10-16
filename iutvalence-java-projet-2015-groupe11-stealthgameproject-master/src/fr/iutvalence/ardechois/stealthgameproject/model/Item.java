package fr.iutvalence.ardechois.stealthgameproject.model;

/**
 * @author vanbossm
 * 
 * */
public class Item
{
	private Position position;

	private boolean isTaken;

	public Item(Position position)
	{
		this.position = position;
	}

	public Position getPosition()
	{
		return position;
	}
	
	public void setPosition(Position position)
	{
		this.position = new Position(position.getX(), position.getY());
	}

	public void setPosition(int x, int y)
	{
		this.position = new Position(x, y);
		
	}

	public boolean isTaken()
	{
		return isTaken;
	}
	
	public void setTaken(Position playerPosition)
	{
		this.isTaken = true;
		this.position = playerPosition;
	}

}
