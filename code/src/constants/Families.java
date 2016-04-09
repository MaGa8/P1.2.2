package constants;

import com.badlogic.ashley.core.Family;
import components.*;

/**
 * class storing family objects for categorization
 * @author martin
 */
public class Families 
{
	public static Family MOVING = Family.all (Position.class, Velocity.class).get();
}
