package components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class Position extends Vector3 implements Component
{
	public Position (float x, float y, float z)
	{
		super (x, y, z);
	}
}
