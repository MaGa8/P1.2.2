package systems;

import logic.ComponentBundle;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class managing a set of component bundles. This class is designed to construct the systems paired with the components
 * in the component bundle.
 * @autor martin
 * created 17.05.2016
 */
public class SystemsTracker
{

	public SystemsTracker()
	{

	}

	/**
	 *
	 * @return A list of newly constructed systems. For each component bundle stored, there is one system in this list.
	 */
	public ArrayList<EntitySystem> systemsInUse()
	{

	}

	/**
	 * @param c a given component bundle
	 * @return true if there is a component bundle stored with the same identifier as c
	 */
	public boolean hasComponent (ComponentBundle c)
	{

	}

	/**
	 * adds b to the set of component bundles, representing the systems in use.
	 * Precondition: no component bundle with the same identifier as b is stored already.
	 * @param b component bundle to track
	 */
	public void track (ComponentBundle b)
	{

	}


	HashSet<ComponentBundle> mSystemsInUse;
}
