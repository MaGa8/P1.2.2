package geometry;

import java.util.ArrayList;
import java.util.Arrays;

import generic.PowersetGenerator;

import com.badlogic.gdx.math.Vector3;

/**
 * class modeling a rectangle in 3 dimensional space
 * @author martin
 */
public class Rectangle extends Shape
{
	public static int INDEPENDENT_VECTORS = 2;
	
	/**
	 * @param offset offset of rectangle
	 * @param directions array of exactly 2 mutually orthogonal vectors. Each point
	 * of the rectangle should be obtainable by a linear combination of the offset and the
	 * unscaled direction vectors.
	 */
	public Rectangle (Vector3 offset, Vector3[] directions)
	{
		assert (directions.length == INDEPENDENT_VECTORS);
		assert (directions[0].dot (directions[1]) == 0f);
		
		mDirections = directions;
		mOffset = offset;
	}
	
	/**
	 * @return the 4 vertices of this rectangle
	 */
	public Vector3[] vertices()
	{
		VertexGenerator gen = new VertexGenerator (mOffset, mDirections);
		return gen.generate();
	}
	
	/**
	 * @return the internal array of 2 direction vectors
	 */
	public Vector3[] directions() { return mDirections; }
	
	/**
	 * @return the internal offset vector
	 */
	public Vector3 offset() { return mOffset; }
	
	
	
	
	private Vector3 mOffset;
	private Vector3[] mDirections;
}
