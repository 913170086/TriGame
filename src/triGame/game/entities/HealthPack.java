package triGame.game.entities;

import objectIO.netObject.NetVar;
import tSquare.game.entity.Entity;
import tSquare.game.entity.EntityKey;

public class HealthPack extends Entity {
	public static final String SPRITE_ID = "HealthPack";
	private static final int minHealth = 20;
	private static final int maxHealth = 100;
	private static final long maxTimeAlive = 60000;
	private static final long minTimeAlive = 30000;
	
	private final NetVar.nInt healthToGive;
	private final NetVar.nLong timeToLive;
	private final long timeSpawned;
	
	private boolean initialized() { return (healthToGive.get() != -1 && timeToLive.get() != -1l); }
	

	public HealthPack(double x, double y, EntityKey key) {
		super(SPRITE_ID, x, y, key);
		timeToLive = new NetVar.nLong(-1l, "timeToLive", objClass);
		healthToGive = new NetVar.nInt(-1, "healthToGive", objClass);
		if (owned()) {
			healthToGive.set((int) (Math.random() * (maxHealth - minHealth)) + minHealth);
			timeToLive.set((long) (Math.random() * (maxTimeAlive - minTimeAlive)) + minTimeAlive);
		}
		timeSpawned = System.currentTimeMillis();
	}
	
	@Override
	public void performLogic(int frameDelta) {
		if (!initialized())
			return;
		if (timeSpawned + timeToLive.get() < System.currentTimeMillis())
			remove();
	}
	
	public int pickUpHealth() {
		if (!initialized() || removeRequested())
			return 0;
		
		remove();
		return healthToGive.get();
	}

}
