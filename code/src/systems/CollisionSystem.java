package systems;


import java.util.HashSet;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;


import constants.Families;

/**
 * @autor martin
 * created 13.05.2016
 */
public class CollisionSystem extends EntitySystem
{

	/**
	 * adds relevant entities of the engine to the system once the system was added to the
	 * system.
	 * @param e engine this was added to
	 */
	public void addedToEngine (Engine e)
	{
		for (Entity add : e.getEntitiesFor (Families.COLLIDING))
		{
			entities().add (add);
			if (Families.ACCELERABLE.matches (add))
				mActive.add (add);
		}
	}

	/**
	 * computes impact of collisions on entities affected
	 * @param dTime time delta to previous state
	 */
	public void update (float dTime)
	{
		//detect collisions
		//for each collision detected
			//if entity 1 is active
				//compute force excerted
			//if entity 2 is active
				//copute force excerted

	}

	/** store impacted by collisions */
	private HashSet<Entity> mActive;
}
