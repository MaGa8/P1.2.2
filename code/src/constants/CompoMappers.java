package constants;

import com.badlogic.ashley.core.*;

import components.*;


/**
 * class storing component mappers
 */
public class CompoMappers 
{
	public static final ComponentMapper<Position> POSITION = ComponentMapper.getFor (Position.class);
	public static final ComponentMapper<Velocity> VELOCITY = ComponentMapper.getFor (Velocity.class);
}
