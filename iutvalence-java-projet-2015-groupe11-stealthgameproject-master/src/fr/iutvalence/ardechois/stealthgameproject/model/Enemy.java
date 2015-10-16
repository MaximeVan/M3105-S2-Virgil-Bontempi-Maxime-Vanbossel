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

	private Position enemyPosition;
	
	private VisionField visionField;
	
	/**
	 * True if the enemy see the target.
	 */
	private boolean see;
	
	public Enemy(Position position, Direction initDir)
	{
		this.enemyPosition = position;
		this.visionField = new VisionField(position, initDir);
		this.see = false;
	}
	
	public Position getPosition()
	{
		return this.enemyPosition;
	}

	public void enemyMovement(Direction direction, Map map) throws InvalidPositionException
	{
		switch (direction)
		{
		case UP:
			if (this.enemyPosition.getY() - 1 < 0)
				throw new InvalidPositionException();
			break;
		case DOWN:
			if (this.enemyPosition.getY() + 1 >= map.getMapHeight())
				throw new InvalidPositionException();
			break;
		case LEFT:
			if (this.enemyPosition.getX() - 1 < 0)
				throw new InvalidPositionException();
			break;
		case RIGHT:
			if (this.enemyPosition.getX() + 1 >= map.getMapWidth())
				throw new InvalidPositionException();
			break;
		}
		
		if(map.getBlock(new Position(this.enemyPosition.getX() + direction.getX(), this.enemyPosition.getY()+direction.getY())) != Blocks.WALL && map.getBlock(new Position(this.enemyPosition.getX() + direction.getX(), this.enemyPosition.getY()+direction.getY())) != Blocks.WATER)
			this.enemyPosition.move(direction);
		
		this.visionField.update(direction);
	}
	
	public boolean getSee()
	{
		return this.see;
	}
	
	public boolean checkVisionField(Player player)
	{
		this.see =  this.visionField.check(player);
		return getSee();
	}
	
	public void randomMove(Map map)
	{
		Random random = new Random();
		
		try
		{
			enemyMovement(Direction.getDirection(random.nextInt(4)), map);
		} catch (InvalidPositionException e)
		{
			// Do nothing
		}
	}

}
