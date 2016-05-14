package collision;

import com.badlogic.ashley.core.Entity;
import components.Body;
import constants.Families;

/**
 * stores colliding entity together with the part of the entity (i.e. a solid it is composed of)
 * causing the collision
 * @autor martin
 * created 13.05.2016
 */
public class Collider
{
	/**
	 *
	 * @param colliding entity colliding
	 * @param collidingPart solid, part of colliding, causing the collision
	 */
	public Collider (Entity colliding, Body collidingPart)
	{
		mColliding = colliding;
		mCollidingPart = collidingPart;
	}

	/**
	 *
	 * @return entitiy stored
	 */
	public Entity getColliding() { return mColliding; }

	/**
	 *
	 * @return solid contained in entity's body involved in collision
	 */
	public Body getmCollidingPart() { return mCollidingPart; }

	/**
	 *
	 * @return true if entity stored is actively involved in collisions
	 */
	public boolean isActive() { return Families.ACCELERABLE.matches (getColliding()); }


	private Entity mColliding;
	private Body mCollidingPart;
}
