package triGame.game.entities.buildings.types;

import tSquare.game.entity.CreationHandler;
import tSquare.game.entity.Entity;
import tSquare.imaging.Sprite;
import triGame.game.ManagerService;
import triGame.game.entities.buildings.Building;
import triGame.game.entities.buildings.Building.BuildingInfo;
import triGame.game.entities.buildings.BuildingCreator;
import triGame.game.entities.buildings.BuildingManager;

public class PointCollectorCreator extends BuildingCreator {
	private final ManagerService managers;
	private final BuildingInfo info;

	public PointCollectorCreator(BuildingInfo info, BuildingManager manager,
			CreationHandler ch, ManagerService managers,
			tSquare.game.entity.LocationCreator.IFace<? extends Building> iFace) {
		
		super(info, manager, ch, managers, iFace);
		this.managers = managers;
		this.info = info;
	}

	@Override
	public boolean isValidLocation(double x, double y) {
		Sprite s = Sprite.get(info.spriteId);
		int w = s.getWidth();
		int h = s.getHeight();
		for (Entity e : managers.person.list) {
			if (e.hitbox.intersects(x, y, w, h) ||
					e.hitbox.contains(x, y, w, h))
				return false;
		}
		return managers.building.objectGrid.isRectangleOpen(x, y, w, h) &&
				!managers.pointWell.objectGrid.isRectangleOpen(x, y, w, h);
	}

}
