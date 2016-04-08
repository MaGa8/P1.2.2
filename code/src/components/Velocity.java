package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.*;

public class Velocity extends Vector3 implements Component 
{
	public Velocity (float x, float y, float z)
	{
		super (x, y, z);
	}
}
