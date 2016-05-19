package systems;

/**
 * Interface for factory classes producing entity systems.
 * @autor martin
 * created 17.05.2016
 */
public interface EntitySystemFactory
{
	/**
	 *
	 * @return a new reference to an entity system.
	 */
	public EntitySystem produce();
	
}
