package constants;

import com.badlogic.ashley.core.Family;
import components.*;

public class Families 
{
	public static Family MOVING = Family.all (Position.class, Velocity.class).get();
}
