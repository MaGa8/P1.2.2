package collision;

/**
 * inner class storing two actors as colliders (i.e. entities) involved in collision
 * @author martin
 */
public class ColliderPair
{
	/**
	 *
	 * @param first collider A involved in collision with collider B
	 * @param second collider B involved in collision with collider A
	 */
	public ColliderPair (Collider first, Collider second)
	{
		mFirst = first;
		mSecond = second;
	}

	public Collider mFirst, mSecond;
}
