package fr.iutvalence.ardechois.stealthgameproject.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Block that compose the map.
 * 
 * @author chayc
 *
 */
/**
 * @author antoine
 *
 */
public enum Blocks
{

	FLOOR('0', "/floor.png"),
	
	WALL('1', "/wall.png"),
	
	GRASS('2', "/grass.png"),
	
	SLAB('3', "/slab.png"),
	
	WATER('4', "/water.png"),
	
	SAND('5', "/sand.png");
	
	private char blockId;

	private Icon icon;

	/**
	 * Give an id to a block.
	 * 
	 * @param id
	 */
	private Blocks(char id, String filename)
	{
		this.blockId = id;
		this.icon = new ImageIcon(getClass().getResource(filename));
	}

	public char getId()
	{
		return this.blockId;
	}

	public Icon getIcon()
	{
		return this.icon;
	}
	
	/**
	 * Get the next block.
	 * @return values()[(ordinal()+1) % values().length]
	 */
	public Blocks getNext()
	{
		return values()[(ordinal()+1) % values().length];
	}
}
