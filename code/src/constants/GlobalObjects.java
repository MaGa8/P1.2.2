package constants;

import logic.Rounder;

/**
 * class storing various global access objects
 * @author martin
 */
public class GlobalObjects 
{
	public static final Rounder ROUND = new Rounder (PhysicsCoefficients.ARITHMETIC_PRECISION);
}
