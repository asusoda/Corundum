package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;

import org.corundummc.entities.living.mobs.Mob;

import com.google.common.eventbus.AllowConcurrentEvents;

public class Villager extends Mob<EntityVillager> {

    protected Villager(EntityVillager entityMC) {
        super(entityMC);
    }

    public Villager() {
        super(new EntityVillager(null));
    }

    /** This class is used to represent the different types of {@link Villager}s. <br>
     * <br>
     * All {@link Villager villagers} actually have the same I.D. (120); all of these {@link VillagerType VillagerTypes} are differentiated by their data value, which in the
     * case of {@link Villager villagers} represents their <a href="http://minecraft.gamepedia.com/Villager#Professions_and_Careers"> profession</a>.
     * 
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class VillagerType<T extends VillagerType<T>> extends MobType<T> {

        @SuppressWarnings("rawtypes")
        public static final VillagerType<?> FARMER = new VillagerType() {
            @Override
            public Farmer create() {
                return new Farmer();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final VillagerType<?> LIBRARIAN = new VillagerType() {
            @Override
            public Librarian create() {
                return new Librarian();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final VillagerType<?> PRIEST = new VillagerType() {
            @Override
            public Priest create() {
                return new Priest();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final VillagerType<?> BLACKSMITH = new VillagerType() {
            @Override
            public Blacksmith create() {
                return new Blacksmith();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final Villager.VillagerType<?> BUTCHER = new VillagerType() {
            @Override
            public Butcher create() {
                return new Butcher();
            }
        };

        private VillagerType() {
            super(120, values() == null ? 0 : values().length);

            addValueAs(Villager.class);
        }

        @Override
        public Villager create() {
            return (Villager) super.create();
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static VillagerType<?> getByID(int id) {
            return getByID(VillagerType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static VillagerType<?> getByID(int id, int data) {
            return getByID(VillagerType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static VillagerType<?>[] values() {
            return values(VillagerType.class);
        }
    }
}
