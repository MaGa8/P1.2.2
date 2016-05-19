package components;

import com.badlogic.ashley.core.Component;

/**
 * interface for classes producing components
 * @author martin
 */
public interface ComponentFactory
{
	public Component produce();
}
