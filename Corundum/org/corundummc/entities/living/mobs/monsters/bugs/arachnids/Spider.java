package org.corundummc.entities.living.mobs.monsters.bugs.arachnids;

import net.minecraft.entity.monster.EntitySpider;
import org.corundummc.entities.living.mobs.monsters.bugs.arachnids.Arachnid.ArachnidType;

public class Spider extends Arachnid<Spider, EntitySpider, Spider.SpiderType> {
    public Spider() {
        super(new EntitySpider(null));
    }

    protected Spider(EntitySpider entityMC) {
        super(entityMC);
    }

    protected static class SpiderType extends ArachnidType<SpiderType, EntitySpider, Spider> {
        public static final SpiderType TYPE = new SpiderType();

        private SpiderType() {
            super(52);
        }

        // overridden utilities
        @Override
        public Spider create() {
            return new Spider();
        }

        @Override
        public Spider fromMC(EntitySpider entityMC) {
            return new Spider(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SpiderType getType() {
        return SpiderType.TYPE;
    }
}