package geometry;

import java.util.ArrayList;
import java.util.Arrays;

import generic.PermutationGenerator;
import generic.PowersetGenerator;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * class modelling a solid of 6 rectangular sides
 * @author martin
 */
public class Box extends Solid<Rectangle>
{
	public static final int	DEFINING_POINTS = 4;
	public static final int SIDES = 6;
	public static final int VERTICES = 8;
	
	/**
	 * @param center central point
	 * @param connected array of points
	 * @return true if the difference from central to every connected is orthogonal to
	 * every other such difference.
	 */
	public static boolean formOrthogonal (Vector3[] connected)
	{
		for (int nCompare = 0; nCompare < connected.length; ++nCompare)
		{
			for (int nComparing = 0; nComparing < connected.length; ++nComparing)
			{
				if (nCompare != nComparing && connected[nCompare].dot (connected[nComparing]) != 0)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @param offset offset vector
	 * @param defining array of exactly 3 vectors, each representing a vertex connected
	 * to offset. Thus the difference of each vector of defining and offset needs to be orthogonal
	 * to every other difference.
	 */
	public Box (Vector3 offset, Vector3[] defining)
	{
		//generate vertices from offset and directions
		super (new VertexGenerator (offset, defining).generate());
		
		if (defining.length != DEFINING_POINTS - 1)
			throw new SolidException ("too " + (defining.length < DEFINING_POINTS - 1 ? "few" : "many") + " defining points passed");
		if (!formOrthogonal (defining))
			throw new SolidException ("points don't form mutually orthogonal edges");
		
		mOffset = offset;
		mDirections = defining;
		mDimensions = new float[DEFINING_POINTS - 1];
		for (int cDir = 0; cDir < mDirections.length; ++cDir)
			mDimensions[cDir] = mDirections[cDir].len();
	}
	
	/**
	 * @return rectangle objects forming this box' sides. 
	 * Note: the rectangle objects do not automatically own their vectors, 
	 * some of them are used in this object too.
	 */
	public Rectangle[] sides()
	{
		int halfSides = SIDES / 2;
		//generate rectangles belonging to offset
		Rectangle[] sides = new Rectangle[SIDES];
		System.arraycopy (sidesOfVertex (mOffset, mDirections), 0, sides, 0, halfSides);
		
		//generate vectors opposite to basis
		Vector3[] oppositeBasis = new Vector3[mDirections.length];
		for (int cBasis = 0; cBasis < mDirections.length; ++cBasis)
			oppositeBasis[cBasis] = mDirections[cBasis].cpy().scl (-1f);
		System.arraycopy (sidesOfVertex (diagonalOffset(), oppositeBasis), 0, sides, halfSides, halfSides);
		
		return sides;
	}
	
	/**
	 * @return the set vectors whose unscaled linear combinations, added to the offset, 
	 * yield any vertex of this box 
	 */
	public Vector3[] getBasis() { return mDirections; }
	
	/**
	 * @return array of lengths of the sides
	 */
	public float[] getDimensions() { return mDimensions; }
	
	/**
	 * @return the box's offset vector
	 */
	public Vector3 getOffset() { return mOffset; }
	
	/**
	 * @return the point diagonally opposite to the offset point. 
	 * Note: needs to be computed, is constructed every time anew.
	 */
	public Vector3 diagonalOffset()
	{
		Vector3 diag = mOffset.cpy();
		for (Vector3 dir : mDirections)
			diag.add (dir);
		return diag;
	}
	
	/**
	 * @param point some point
	 * @return true if point is within this box
	 */
	public boolean isWithin (Vector3 point)
	{
		Vector3 diagonal = diagonalOffset();
		
		float minX, maxX, minY, maxY, minZ, maxZ;
		minX = Math.min (getOffset().x, diagonal.x);
		maxX = Math.max (getOffset().x, diagonal.x);
		minY = Math.min (getOffset().y, diagonal.y);
		maxY = Math.max (getOffset().y, diagonal.y);
		minZ = Math.min (getOffset().z, diagonal.z);
		maxZ = Math.max (getOffset().z, diagonal.z);
		
		return (point.x > minX && point.x < maxX && 
				point.y > minY && point.y < maxY && 
				point.z > minZ && point.z < maxZ);
	}
	
	/**
	 * @param vertex a vertex of this box
	 * @param dirs direction vectors defining the box from vertex on
	 * @return all sides vertex is part of
	 */
	private Rectangle[] sidesOfVertex (Vector3 vertex, Vector3[] dirs)
	{
		//generate 2 vector powerset
		PowersetGenerator<Vector3> gen2 = new PowersetGenerator<> (Arrays.asList (dirs));
		Rectangle[] rects = new Rectangle[SIDES / 2];
		
		//for each element of 2 vector powerset
		int cnt = 0;
		for (ArrayList<Vector3> elem : gen2.generate(2, 2))
		{
			//generate vertices
			VertexGenerator vertexGen = new VertexGenerator (vertex, (Vector3[]) elem.toArray());
			//construct rectangle
			rects[cnt] = new Rectangle (vertex, (Vector3[]) elem.toArray());
		}
		
		return rects;
	}
	
	private Vector3 mOffset;
	private Vector3[] mDirections;
	private float[] mDimensions;
}
