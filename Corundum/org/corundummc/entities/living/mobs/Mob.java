package org.corundummc.entities.living.mobs;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;
import org.corundummc.items.Item;
import org.corundummc.types.Creatable;

/** This class represents a "mob", an autonomous A.I.-controlled {@link LivingEntity}. This includes things like {@link Monster monsters}, {@link Animal animals}, and even
 * {@link Boss bosses}.
 * 
 * @param <T>
 *            determines the type of {@link net.minecraft.entity.Entity Minecraft Entity} that this class represents. */
public abstract class Mob<T extends EntityLiving> extends LivingEntity<T> {
    @SuppressWarnings("javadoc")
    protected Mob(T entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link Mob}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #FARMER farmers} and {@link #LIBRARIAN librarians} are both represented as separate types despite the fact that they both have the same I.D. value.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class MobType<T extends LivingEntityType<T>> extends LivingEntityType<T> {

        public static final MobType<?> CREEPER = MonsterType.CREEPER;
        public static final MobType<?> SKELETON = MonsterType.SKELETON;
        public static final MobType<?> WITHER_SKELETON = MonsterType.WITHER_SKELETON;
        public static final MobType<?> SPIDER = MonsterType.SPIDER;
        public static final MobType<?> GIANT = MonsterType.GIANT;
        public static final MobType<?> ZOMBIE = MonsterType.ZOMBIE;
        public static final MobType<?> ZOMBIFIED_VILLAGER = MonsterType.ZOMBIFIED_VILLAGER;
        public static final MobType<?> SLIME = MonsterType.SLIME;
        public static final MobType<?> GHAST = MonsterType.GHAST;
        public static final MobType<?> ZOMBIE_PIGMAN = MonsterType.ZOMBIE_PIGMAN;
        public static final MobType<?> ENDERMAN = MonsterType.ENDERMAN;
        public static final MobType<?> CAVE_SPIDER = MonsterType.CAVE_SPIDER;
        public static final MobType<?> SILVERFISH = MonsterType.SILVERFISH;
        public static final MobType<?> BLAZE = MonsterType.BLAZE;
        public static final MobType<?> MAGMA_CUBE = MonsterType.MAGMA_CUBE;
        public static final MobType<?> ENDER_DRAGON = MonsterType.ENDER_DRAGON;
        public static final MobType<?> WITHER = MonsterType.WITHER;
        public static final MobType<?> WITCH = MonsterType.WITCH;
        public static final MobType<?> ENDERMITE = MonsterType.ENDERMITE;
        public static final MobType<?> GUARDIAN = MonsterType.GUARDIAN;
        public static final MobType<?> ELDER_GUARDIAN = MonsterType.ELDER_GUARDIAN;
        public static final MobType<?> BAT = AnimalType.BAT;
        public static final MobType<?> PIG = AnimalType.PIG;
        public static final MobType<?> SHEEP = AnimalType.SHEEP;
        public static final MobType<?> COW = AnimalType.COW;
        public static final MobType<?> CHICKEN = AnimalType.CHICKEN;
        public static final MobType<?> SQUID = AnimalType.SQUID;
        public static final MobType<?> WOLF = AnimalType.WOLF;
        public static final MobType<?> MOOSHROOM = AnimalType.MOOSHROOM;
        public static final MobType<?> HORSE = AnimalType.HORSE;
        public static final MobType<?> RABBIT = AnimalType.RABBIT;
        public static final MobType<?> KILLER_RABBIT = AnimalType.KILLER_RABBIT;
        public static final MobType<?> OCELOT = AnimalType.OCELOT;
        public static final MobType<?> SNOW_GOLEM = GolemType.SNOW_GOLEM;
        public static final MobType<?> IRON_GOLEM = GolemType.IRON_GOLEM;
        public static final MobType<?> FARMER = VillagerType.FARMER;
        public static final MobType<?> LIBRARIAN = VillagerType.LIBRARIAN;
        public static final MobType<?> PRIEST = VillagerType.PRIEST;
        public static final MobType<?> BLACKSMITH = VillagerType.BLACKSMITH;
        public static final MobType<?> BUTCHER = VillagerType.BUTCHER;

        protected MobType(int id, int data) {
            super(id, data);

            addValueAs(MobType.class);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static MobType<?> getByID(int id) {
            return getByID(MobType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static MobType<?> getByID(int id, int data) {
            return getByID(MobType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static MobType<?>[] values() {
            return values(MobType.class);
        }
    }
}
