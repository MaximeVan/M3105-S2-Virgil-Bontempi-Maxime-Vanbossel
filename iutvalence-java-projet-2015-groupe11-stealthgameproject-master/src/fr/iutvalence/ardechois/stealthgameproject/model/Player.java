package fr.iutvalence.ardechois.stealthgameproject.model;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;
import fr.iutvalence.ardechois.stealthgameproject.view.PlayerGetter;

/**
 * @author kelemenn
 *
 */
public class Player implements PlayerGetter
{
	private Position position;

	public Player(Position position)
	{
		this.position = new Position(position.getX(), position.getY());
	}

	public Position getPosition()
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
		
		Blocks nextBlock = map.getBlock(new Position(this.position.getX() + direction.getX(), this.position.getY()+direction.getY()));
		if(nextBlock != Blocks.WALL && nextBlock != Blocks.WATER)
			this.position.move(direction);
	}
}
