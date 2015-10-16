package fr.iutvalence.ardechois.stealthgameproject.model;

/**
 * @author kelemenn
 * 
 * @version 0.1.0
 */
public enum Direction

{
	UP(0, -1),

	DOWN(0, 1),

	LEFT(-1, 0),

	RIGHT(1, 0);

	/**
	 * Delta X move value.
	 */
	private int x;
	/**
	 * Delta X move value.
	 */
	private int y;

	private Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	/**
	 * Get the direction of the given id.
	 * @param id
	 * @return Direction.
	 */
	public static Direction getDirection(int id)
	{
		return values()[id];
	}
}