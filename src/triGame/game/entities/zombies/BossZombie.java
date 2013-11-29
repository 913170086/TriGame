package triGame.game.entities.zombies;

import objectIO.connections.Connection;
import objectIO.netObject.NetVar;
import tSquare.game.entity.EntityKey;
import triGame.game.ManagerService;
import triGame.game.entities.HealthBar;

public class BossZombie extends Zombie {
	public static final String SPRITE_ID = "bossZombie";
	
	private final HealthBar healthBar;
	private final NetVar.nInt maxHealth;

	public BossZombie(double x, double y, ManagerService managers,
			boolean isServer, ZombiePathFinder pathFinder, EntityKey key) {
		
		super(SPRITE_ID, x, y, managers, isServer, pathFinder, key);
		healthBar = new HealthBar(this);
		maxHealth = new NetVar.nInt(100, "maxHealth", objClass);
		maxHealth.event = new NetVar.OnChange<Integer>() {
			@Override
			public void onChange(NetVar<Integer> var, Connection c) {
				healthBar.maxHealth = var.get();
			}
		};
	}
	
	void setMaxHealth(int max) {
		int actual = (int) (max - getHealth());
		maxHealth.set(actual);
		modifyHealth(max - getHealth());
		healthBar.maxHealth = actual;
	}

	@Override
	public void hitByProjectile(int damage) {
		modifyHealth(damage);
	}

}