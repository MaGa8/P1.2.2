package collision;

import com.badlogic.ashley.core.Entity;
import com.sun.org.apache.xerces.internal.dom.EntityImpl;
import components.Body;
import constants.CompoMappers;
import constants.Families;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * class detecting collisions between entities
 * @autor martin
 * created 13.05.2016
 */
public class CollisionDetector
{
	/**
	 * class storing an entity together with its body
	 */
	public class BodyPair
	{
		/**
		 *
		 * @param e entity belonging to the colliding family
		 */
		public BodyPair (Entity e)
		{
			mEntity = e;
			mBody = CompoMappers.BODY.get (e);
		}


		public boolean equals (BodyPair comp) { return this.mEntity.equals (comp.mEntity); }

		public Body mBody;
		public Entity mEntity;
	}

	/**
	 * inner class storing two actors as colliders (i.e. entities) involved in collision
	 */
	public class ColliderPair
	{
		/**
		 *
		 * @param first collider A involved in collision with collider B
		 * @param second collider B involved in collision with collider A
		 */
		public ColliderPair (Collider first, Collider second)
		{
			mFirst = first;
			mSecond = second;
		}

		public Collider mFirst, mSecond;
	}

	/**
	 *
	 * @return a collider for each unordered pair of (active|passive) or (active/active) entities
	 */
	public ArrayList<ColliderPair> getAnyColliding()
	{

	}


	/**
	 *
	 * @return true if there is any collision between a pair of (active, active) or (active/passive) entities
	 */
	public boolean collision()
	{

	}

	/**
	 * adds e to the set of entities to check for collisions with.
	 * @param e an entity belonging to the colliding family
	 * @throws IllegalArgumentException if e was already stored
	 */
	public void add (Entity e)
	{
		BodyPair ePair = new BodyPair (e);
		boolean notPresent = mAll.add (ePair);
		if (!notPresent)
			throw new IllegalArgumentException ("entity " + e + " was already contained");
		if (Families.ACCELERABLE.matches (e))
			mActive.add (ePair);
	}

	/**
	 * removes  e from the set of entities to check for collisions if e is stored there.
	 * Otherwise nothing happens.
	 * @param e an entity
	 */
	public void remove (Entity e)
	{
		BodyPair ePair = new BodyPair (e);
		if (Families.ACCELERABLE.matches (e))
			mActive.remove (ePair);
		mAll.remove (ePair);
	}

	private Set<BodyPair> mActive, mAll;
}
