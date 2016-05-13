package geometry.spatial;

import java.util.Arrays;
import java.util.TreeSet;

import com.badlogic.gdx.math.Vector3;
import geometry.planar.Shape;

public abstract class Solid
{
	/** number of independent vectors spanning solid */
	public static final int INDEP_VECS = 3;


	/**
	 * class to be thrown if invalid arguments are passed to a method
	 * of a solid object.
	 * @author martin
	 */
	public static class SolidException extends IllegalArgumentException
	{
		public SolidException (String message) { super (message); }
	}
	
	/**
	 * parametric constructor
	 * @param vertices vertices of solid
	 */
	public Solid (Vector3[] vertices, Shape[] sides)
	{
		mVertices = vertices;
		mSides = sides;
	}
	
	/**
	 * @return vertices stored
	 */
	public Vector3[] getVertices() { return mVertices; }
	
	/**
	 * @return sides of solid
	 */
	public Shape[] getSides() { return mSides; }
	
	/**
	 * @param p a point, given by vector
	 * @return true if p is within the solid, false otherwise
	 */
	public abstract boolean isWithin (Vector3 p);

	/**
	 * tests equality for solids
	 * @param comp solid to compare with
	 * @return true if all vertices of this are vertices of comp and all vertices of comp are contained in this
	 */
	public boolean equals (Solid comp)
	{
		TreeSet<Vector3> tVertices = new TreeSet<> (Arrays.asList (mVertices));
		TreeSet<Vector3> compVertices = new TreeSet<> (Arrays.asList (comp.getVertices()));

		return (tVertices.containsAll (compVertices) && compVertices.containsAll (tVertices));
	}
	
	
	private Vector3[] mVertices;
	private Shape[] mSides;
}
