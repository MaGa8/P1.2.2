package testing;

import com.badlogic.gdx.math.*;

/**
 * groups static utility methods for dealing with vectors
 * @author martin
 */
public class VectorUtil 
{
	public static Vector3 construct (float x, float y, float z)
	{
		float[] coords = {x, y, z};
		return new Vector3 (coords);
	}
}
