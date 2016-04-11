package systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import constants.CompoMappers;
import constants.Families;
import components.Force;
import components.GravityForce;

public class Gravity extends EntitySystem 
{
	public Gravity()
	{
		
	}
	
	public void addedToEngine (Engine e)
	{
		for (Entity add : e.getEntitiesFor (Families.GRAVITY_ATTRACTED))
			entities().add (add);
	}
	
	/**
	 * applies gravity to all entities being GRAVITY_ATTRACTED, 
	 * thus adding gravity attraction to their force component
	 */
	public void update (float dTime)
	{
		for (Entity update : entities())
		{
			float mass = CompoMappers.MASS.get (update).mMass;
			Force f = CompoMappers.FORCE.get (update);
			GravityForce g = CompoMappers.GRAVITY_FORCE.get (update);
			
			f.add (g.cpy().scl (mass));
		}
	}
}
