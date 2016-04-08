package systems;

import java.util.*;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.*;
import com.badlogic.gdx.utils.Array;

import constants.Families;

public class Movement extends EntitySystem
{
	
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
	
	
	public Movement (Engine e)
	{
		mMovingEntities = new HashSet<>();
		for (Entity ent : e.getEntitiesFor (Families.MOVING))
			mMovingEntities.add (ent);
	}
	
	
	private HashSet<Entity> mMovingEntities;
}
