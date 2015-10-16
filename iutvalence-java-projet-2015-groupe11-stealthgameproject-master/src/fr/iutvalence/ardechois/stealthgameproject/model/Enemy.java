package fr.iutvalence.ardechois.stealthgameproject.model;

import java.util.Random;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;

/**
 * @author kelemenn
 * 
 * @version 0.1.0
 */
public class Enemy
{

	private Position position;
	
	private VisionField visionField;
	
	/**
	 * True if the enemy see the target.
	 */
	private boolean isSeen;
	
	public Enemy(Position position, Direction initDir)
	{
		this.position = position;
		this.visionField = new VisionField(position, initDir);
		this.isSeen = false;
	}
	
	public Position position()
	{
		return this.position;
	}

	public void move(Direction direction, Map map) throws InvalidPositionException
	{
		switch (direction)
		{
		case UP:
			if (this.position.getY() - 1 < 0)
				throw new InvalidPositionException();
			break;
		case DOWN:
			if (this.position.getY() + 1 >= map.getMapHeight())
				throw new InvalidPositionException();
			break;
		case LEFT:
			if (this.position.getX() - 1 < 0)
				throw new InvalidPositionException();
			break;
		case RIGHT:
			if (this.position.getX() + 1 >= map.getMapWidth())
				throw new InvalidPositionException();
			break;
		}
		
		if(map.getBlock(new Position(this.position.getX() + direction.getX(), this.position.getY()+direction.getY())) != Blocks.WALL && map.getBlock(new Position(this.position.getX() + direction.getX(), this.position.getY()+direction.getY())) != Blocks.WATER)
			this.position.move(direction);
		
		this.visionField.update(direction);
	}
	
	public boolean getSee()
	{
		return this.isSeen;
	}
	
	public boolean visionFieldIsChecked(Player player)
	{
		this.isSeen =  this.visionField.isChecked(player);
		return getSee();
	}
	
	public void randomMove(Map map)
	{
		Random random = new Random();
		
		try
		{
			move(Direction.getDirection(random.nextInt(4)), map);
		} catch (InvalidPositionException e)
		{
			// Do nothing
		}
	}

}
