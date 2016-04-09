package systems;

import java.util.*;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.*;
import com.badlogic.gdx.utils.Array;

import constants.CompoMappers;
import constants.Families;

import components.Position;
import components.Velocity;

/**
 * entity system applying movement to every entity
 * @author martin
 */
public class Movement extends EntitySystem
{
	/**
	 * listener to attach to engine to update entities which need to be moved
	 * @author martin
	 */
	public class NewEntitiesListener implements EntityListener
	{
		public void entityAdded (Entity added)
		{
			mMovingEntities.add (added);
		}
		
		public void entityRemoved (Entity removed)
		{
			mMovingEntities.remove (removed);
		}
	}
	
	/**
	 * default constructor
	 */
	public Movement()
	{
		mMovingEntities = new HashSet<>();
	}
	
	/**
	 * @param e engine to which this was added
	 * adds all moveable entities of e to this system
	 * is automatically called by engine
	 */
	public void addedToEngine (Engine e)
	{
		for (Entity ent : e.getEntitiesFor (Families.MOVING))
			mMovingEntities.add (ent);
	}
	
	/**
	 * action happening on update of engine
	 * moves all entities proportionally to the time elapsed
	 */
	public void update (float dTime)
	{
		for (Entity move : mMovingEntities)
		{
			Position p = CompoMappers.POSITION.get (move);
			Velocity v = CompoMappers.VELOCITY.get (move);
			
			p.mulAdd (v, dTime);
		}
	}
	
	
	private HashSet<Entity> mMovingEntities;
}
