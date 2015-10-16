package fr.iutvalence.ardechois.stealthgameproject.model;

import java.io.File;
import java.util.ArrayList;
import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidMapSizeException;
import fr.iutvalence.ardechois.stealthgameproject.view.LevelGetter;

/**
 * @author antoine
 * @version 0.1.0
 *
 */
public class Level implements LevelGetter
{
	private Map currentMap;

	private ArrayList<Enemy> enemyList;

	private Item currentItem;

	public Level()
	{
		currentItem = new Item(new Position(0, 0));
		enemyList = new ArrayList<Enemy>();

		try
		{
			currentMap = new Map("tempMap.txt", currentItem, this);
		} catch (InvalidMapSizeException e)
		{
			e.printStackTrace();
		}
	}

	public Level(File file)
	{
		currentItem = new Item(new Position(0, 0));
		enemyList = new ArrayList<Enemy>();
		currentMap = new Map(file, currentItem, this);
	}

	public Map getCurrentMap()
	{
		return currentMap;
	}

	public Item getCurrentItem()
	{
		return currentItem;
	}

	public void setCurrentItem(Item currentItem)
	{
		this.currentItem = currentItem;
	}

	@Override
	public Position getItemPosition()
	{
		return currentItem.getPosition();
	}

	/**
	 * Check if the player is in a vision field.
	 * @param player
	 * @return
	 */
	public boolean allVisionFieldsAreChecked(Player player)
	{
		for (Enemy enemy : enemyList)
		{
			if (enemy.visionFieldIsChecked(player))
				return true;
		}
		return false;
	}

	public void updateItem(Player player)
	{
		if (player.getPosition().getX() == currentItem.getPosition().getX()
				&& player.getPosition().getY() == currentItem.getPosition().getY())
		{
			currentItem.setTaken(player.getPosition());
		}
	}

	public boolean hasWon(Player player)
	{
		return (currentItem.isTaken() && (currentMap.getSpawnPosition().getX() == player.getPosition().getX() && currentMap
				.getSpawnPosition().getY() == player.getPosition().getY()));
	}

	@Override
	public ArrayList<Position> getEnemiesPositions()
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		for (Enemy enemy : enemyList)
		{
			positions.add(enemy.position());
		}

		return positions;
	}

	public void moveEnemies()
	{
		for (Enemy enemy : enemyList)
		{
			enemy.randomMove(currentMap);
		}

	}

	public void addEnemy(Position position)
	{
		boolean exists = false;
		for (Enemy enemy : enemyList)
		{
			if(enemy.position().getX() == position.getX() && enemy.position().getY() == position.getY())
			{
				exists = true;
				enemyList.remove(enemy);
				break;
			}
		}

		if(!exists)
			enemyList.add(new Enemy(new Position(position.getX(), position.getY()), Direction.UP));
	}

	public void reset()
	{
		enemyList.clear();
		currentMap.reset();
	}
}
