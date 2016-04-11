package systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.*;

import constants.CompoMappers;
import constants.Families;

import components.*;

public class ForceApply extends EntitySystem 
{
	public ForceApply()
	{
		
	}
	
	public void addedToEngine (Engine e)
	{
		for (Entity add : e.getEntitiesFor (Families.ACCELERABLE))
			entities().add (add);
	}
	
	/**
	 * alters velocity of entities\n
	 * uses accumulated force in force component, 
	 * computes resulting acceleration given by a = F / m and 
	 * adds 0.5 * a^2 to the velocity vector
	 */
	public void update (float dTime)
	{
		for (Entity update : entities())
		{
			Force f = CompoMappers.FORCE.get (update);
			Mass m = CompoMappers.MASS.get (update);
			Velocity v = CompoMappers.VELOCITY.get (update);
			
			//F=m*a
			Vector3 a = f.cpy();
			a.scl (1 / m.mMass);
			
			//dv = 1/2 * a^2
			a.scl (a);
			v.add (a.scl (0.5f));
			
			f.setZero();
		}
	}
	
}
