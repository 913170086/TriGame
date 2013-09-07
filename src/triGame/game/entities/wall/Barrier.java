package triGame.game.entities.wall;

import triGame.game.shopping.ShopItem;


public class Barrier extends AbstractWall {
	public static final String SPRITE_ID = "barrier";
	public static final String IDENTIFIER = "barrier";
	public static final ShopItem NEW_BARRIER = new ShopItem("Barrier", 10);
	
	public Barrier(int x, int y, WallManager manager, long entityId) {
		super(SPRITE_ID, x, y, manager, entityId, IDENTIFIER);
	}
	
	public static Barrier create(int x, int y, WallManager manager) {
		if (manager.objectGrid.isBlockOpen(x, y)) {
			Barrier barrier = new Barrier(x, y, manager, manager.getUniqueId());
			barrier.createOnNetwork(false);
			manager.add(barrier);
			return barrier;
		}
		return null;
	}
	
	public static Barrier createFromString(String parameters, WallManager manager, long entityId) {
		String[] parameter = parameters.split(":");
		int x = Integer.parseInt(parameter[1]);
		int y = Integer.parseInt(parameter[2]);
		Barrier barrier = new Barrier(x, y, manager, entityId);
		return barrier;
	}

}
