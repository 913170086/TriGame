package triGame.game.entities.buildings;

import java.awt.Graphics2D;

import tSquare.game.GameBoard.ViewRect;
import tSquare.game.entity.EntityKey;
import tSquare.imaging.AnimatedSprite;
import tSquare.imaging.Sprite;
import triGame.game.shopping.ShopItem;

public abstract class Wall extends Building {
	public static final String CRACKS_SPRITE_ID = "media/WallCracks.png";
	private final AnimatedSprite cracks = AnimatedSprite.GET(CRACKS_SPRITE_ID);

	public Wall(String spriteId, double x, double y, BuildingInfo info,
			EntityKey key) {
		super(spriteId, x, y, null, info, key);
	}
	
	public static class WallInfo extends BuildingInfo {
		
		public WallInfo(String spriteId, String identifier,
				String description, ShopItem item, int maxHealth) {
			
			super(spriteId, identifier, 0, description, item, false, false, false, 10, maxHealth);
		}
	}
	
	@Override
	public void draw(Graphics2D g, ViewRect rect) {
		if (!visible || !isOnScreen(rect))
			return;
		
		super.draw(g, rect);
		
		int index = (int) ((getHealth() * cracks.total) / info.maxHealth);
		index = 3 - 1 - index;
		if (getHealth() < info.maxHealth && index >= 0 && index < cracks.total) {
			Sprite s = cracks.sprites[index];
			int x = (int) (getX() - rect.getX());
			int y = (int) (getY() - rect.getY());
			
			s.draw(x, y, g);
		}
	}

}
