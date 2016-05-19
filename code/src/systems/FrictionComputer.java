package systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import components.Friction;
import components.GravityForce;
import components.Spin;
import components.Velocity;

import constants.CompoMappers;
import constants.Families;

/**
 * Class storing an entity belonging to the friction family.\n
 * Performs calculations related to friction based on the current
 * state of the entity stored.
 * @author martin
 */
public class FrictionComputer 
{
	/**
	 * @param entity entity to perform friction calculations for; 
	 * entity will not be altered; 
	 * entity needs to belong to the friction family (assertion safeguard in place)
	 */
	public FrictionComputer (Entity entity)
	{
		assert (Families.FRICTION.matches (entity));
		mEntity = entity;
	}
	
	/**
	 * @return friction vector opposing the movment of the entity
	 */
	public Vector3 getMovingFriction ()
	{
		assert (Families.MOVING.matches (mEntity));
		
		Friction fCoeff = CompoMappers.FRICTION.get (mEntity);
		Velocity v = CompoMappers.VELOCITY.get (mEntity);
		GravityForce g = CompoMappers.GRAVITY_FORCE.get (mEntity);
		//clone velocity, set length proportional to gravity, rotate by 180 degrees
		Vector3 fric = v.cpy();
		fric.setLength (g.len() * fCoeff.get (Friction.State.DYNAMIC, Friction.Type.MOVE));
		return fric.scl (-1f);
	}
	
	/**
	 * NOT FUNCTIONAL YET
	 * @return friction vector opposing the spinning of the entity
	 */
	/*
	 * @TODO get and use spinning velocity
	 */
	Vector3 getSpinningFriction ()
	{
		assert (Families.SPINNING.matches (mEntity));
		
		Friction fCoeff = CompoMappers.FRICTION.get (mEntity);
		Spin s = CompoMappers.SPIN.get (mEntity);
		
		
		return null;
	}
	
	private Entity mEntity;
}
