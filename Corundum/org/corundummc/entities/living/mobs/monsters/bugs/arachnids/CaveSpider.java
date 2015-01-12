package org.corundummc.entities.living.mobs.monsters.bugs.arachnids;

import net.minecraft.entity.monster.EntityCaveSpider;
import org.corundummc.entities.living.mobs.monsters.bugs.arachnids.Arachnid.ArachnidType;

public class CaveSpider extends Arachnid<CaveSpider, EntityCaveSpider, CaveSpider.CaveSpiderType> {
    public CaveSpider() {
        super(new EntityCaveSpider(null));
    }

    protected CaveSpider(EntityCaveSpider entityMC) {
        super(entityMC);
    }

    protected static class CaveSpiderType extends ArachnidType<CaveSpiderType, EntityCaveSpider, CaveSpider> {
        public static final CaveSpiderType TYPE = new CaveSpiderType();

        private CaveSpiderType() {
            super(59);
        }

        // overridden utilities
        @Override
        public CaveSpider create() {
            return new CaveSpider();
        }

        @Override
        public CaveSpider fromMC(EntityCaveSpider entityMC) {
            return new CaveSpider(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public CaveSpiderType getType() {
        return CaveSpiderType.TYPE;
    }
}