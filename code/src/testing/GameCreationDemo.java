package testing;

import com.badlogic.ashley.core.Engine;
import components.ComponentFactory;
import components.Position;
import constants.CompoMappers;
import entities.Ball;
import entities.EntityFactory;
import entities.Hole;
import logic.ComponentBundle;
import logic.Game;
import logic.GameConfigurator;
import logic.Player;
import systems.EntitySystemFactory;

/**
 * demonstration: how to use the game engine
 * @autor martin
 * created 19.05.2016
 */
public class GameCreationDemo
{
	public static void main (String[] args)
	{
		// instantiate component bundles to hold pairing of components and (possibly null) systems
		ComponentBundle position = position();
		ComponentBundle velocity = velocity();
		ComponentBundle force = force();

		//construct game configurator
		GameConfigurator config = new GameConfigurator();
		//obtain entity factory for entities moving and affected by forces
		EntityFactory basicMovingEntities = config.entityFactory();
		basicMovingEntities.addComponent (position, velocity, force);

		//add entities to game configuration/engine
		config.addEntities (basicMovingEntities, 2);
		//modify factories stored in component bundles
		//add entities using entity factory

		//add balls
		Ball b1 = new Ball (basicMovingEntities.produce());
		//modify factories again to (i.e.) change position of 2nd ball
		Ball b2 = new Ball (basicMovingEntities.produce());

		Player p1 = new Player ("p1");
		Player p2 = new Player ("p2");

		config.addBall (p1, b1);
		config.addBall (p2, b2);

		//set hole
		EntityFactory holeEntityFax = config.entityFactory();
		holeEntityFax.addComponent (position);
		Hole h = new Hole (holeEntityFax.produce());
		config.setHole (h);

		//get game after setup is done
		Game game = config.game();

		//hitting the ball of the current player
		//will make the game busy, so no hit can be executed while the ball is still moving
		//after it's the next player's turn
		game.hit ();

		//check whether the game is busy
		game.isBusy();

		//getting ball of player 1
		game.getBall (p1);

		//@TODO enable cloning of games

	}


	public static ComponentBundle position()
	{
		ComponentFactory compFax = null;
		return new ComponentBundle (compFax, null);
	}


	public static ComponentBundle velocity()
	{
		ComponentFactory compFax = null;
		EntitySystemFactory entFax = null;
		return new ComponentBundle (compFax, entFax);
	}

	public static ComponentBundle force()
	{
		ComponentFactory compFax = null;
		EntitySystemFactory entFax = null;
		return new ComponentBundle (compFax, entFax);
	}


}
