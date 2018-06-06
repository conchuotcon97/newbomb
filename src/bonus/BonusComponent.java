package bonus;

import model.Entity;
import model.EntityManager;
import model.Position;

public abstract class BonusComponent extends Entity {
	public BonusComponent(Position po, EntityManager manager) {
		super (po,manager);
	}
	public abstract void bonnus() ;
		
}
