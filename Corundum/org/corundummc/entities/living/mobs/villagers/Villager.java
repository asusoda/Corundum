package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;

import org.corundummc.entities.living.mobs.Ageable;
import org.corundummc.entities.living.mobs.Mob;
import org.corundummc.entities.living.mobs.Mob.MobType;
import org.corundummc.entities.living.mobs.villagers.Blacksmith.BlacksmithType;
import org.corundummc.entities.living.mobs.villagers.Butcher.ButcherType;
import org.corundummc.entities.living.mobs.villagers.Farmer.FarmerType;
import org.corundummc.entities.living.mobs.villagers.Librarian.LibrarianType;
import org.corundummc.entities.living.mobs.villagers.Priest.PriestType;
import org.corundummc.utils.types.IDedType;

import com.google.common.eventbus.AllowConcurrentEvents;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Villager<S extends Villager<S, T>, T extends Villager.VillagerType<T, S>> extends Mob<S, EntityVillager, T> implements Ageable {
    protected Villager() {
        this(new EntityVillager(null));
    }

    protected Villager(EntityVillager entityMC) {
        super(entityMC);

        entityMC.setProfession(getProfession().getID());
    }

    public static interface VillagerTypes {
        public static final FarmerType FARMER = FarmerType.TYPE;
        public static final LibrarianType LIBRARIAN = LibrarianType.TYPE;
        public static final PriestType PRIEST = PriestType.TYPE;
        public static final BlacksmithType BLACKSMITH = BlacksmithType.TYPE;
        public static final ButcherType BUTCHER = ButcherType.TYPE;
    }

    public abstract static class VillagerType<S extends VillagerType<S, I>, I extends Villager<I, S>> extends MobType<S, EntityVillager, I> {
        protected VillagerType(int data) {
            super(120, data);

            addValueAs(VillagerType.class);
        }

        // instance utilities
        @SuppressWarnings("unchecked")
        public Profession<S> getProfession() {
            return Profession.getByID(getData());
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static VillagerType getByID(int id) {
            return getByID(VillagerType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static VillagerType getByID(int id, int data) {
            return getByID(VillagerType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static VillagerType[] values() {
            return values(VillagerType.class);
        }
    }

    public static interface Professions {
        public static final Profession<FarmerType> FARMER = new Profession<>("farmer");
        public static final Profession<LibrarianType> LIBRARIAN = new Profession<>("librarian");
        public static final Profession<PriestType> PRIEST = new Profession<>("priest");
        public static final Profession<BlacksmithType> BLACKSMITH = new Profession<>("blacksmith");
        public static final Profession<ButcherType> BUTCHER = new Profession<>("butcher");
    }

    public static class Profession<V extends VillagerType<V, ?>> extends IDedType<Profession<V>> {
        private final String name;

        private Profession(String name) {
            super(nextID(Profession.class));

            addValueAs(Profession.class);

            this.name = name;
        }

        // overridden utilities
        @Override
        public String getName() {
            return name;
        }

        // instance utilities
        // TODO: public Career[] getCareers();

        @SuppressWarnings("unchecked")
        public V getVillagerType() {
            return (V) VillagerType.values()[getID()];
        }

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static Profession getByID(int id) {
            return getByID(Profession.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static Profession[] values() {
            return values(Profession.class);
        }

    }

    // type utilities

    // instance utilities
    public Profession<T> getProfession() {
        return getType().getProfession();
    }

    @Override
    public boolean isABaby() {
        return entityMC.getGrowingAge() < 0;
    }
}
