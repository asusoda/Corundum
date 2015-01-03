package org.corundummc.entities.living.mobs;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.animals.Animal.AnimalType;
import org.corundummc.entities.living.mobs.golems.Golem.GolemType;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;
import org.corundummc.items.Item;

/** This class represents a "mob", an autonomous A.I.-controlled {@link LivingEntity}. This includes things like {@link Monster monsters}, {@link Animal animals}, and even
 * {@link Boss bosses}. */
public abstract class Mob extends LivingEntity {
    @SuppressWarnings("javadoc")
    protected Mob(EntityLiving entityMC) {
        super(entityMC);
    }

    public static class MobTypes {
        public static final MobType CREEPER = MonsterTypes.CREEPER;
        public static final MobType SKELETON = MonsterTypes.SKELETON;
        public static final MobType WITHER_SKELETON = MonsterTypes.WITHER_SKELETON;
        public static final MobType SPIDER = MonsterTypes.SPIDER;
        public static final MobType GIANT = MonsterTypes.GIANT;
        public static final MobType ZOMBIE = MonsterTypes.ZOMBIE;
        public static final MobType ZOMBIFIED_VILLAGER = MonsterTypes.ZOMBIFIED_VILLAGER;
        public static final MobType SLIME = MonsterTypes.SLIME;
        public static final MobType GHAST = MonsterTypes.GHAST;
        public static final MobType ZOMBIE_PIGMAN = MonsterTypes.ZOMBIE_PIGMAN;
        public static final MobType ENDERMAN = MonsterTypes.ENDERMAN;
        public static final MobType CAVE_SPIDER = MonsterTypes.CAVE_SPIDER;
        public static final MobType SILVERFISH = MonsterTypes.SILVERFISH;
        public static final MobType BLAZE = MonsterTypes.BLAZE;
        public static final MobType MAGMA_CUBE = MonsterTypes.MAGMA_CUBE;
        public static final MobType ENDER_DRAGON = MonsterTypes.ENDER_DRAGON;
        public static final MobType WITHER = MonsterTypes.WITHER;
        public static final MobType WITCH = MonsterTypes.WITCH;
        public static final MobType ENDERMITE = MonsterTypes.ENDERMITE;
        public static final MobType GUARDIAN = MonsterTypes.GUARDIAN;
        public static final MobType ELDER_GUARDIAN = MonsterTypes.ELDER_GUARDIAN;

        public static final MobType PIG = AnimalTypes.PIG;
        public static final MobType SHEEP = AnimalTypes.SHEEP;
        public static final MobType COW = AnimalTypes.COW;
        public static final MobType CHICKEN = AnimalTypes.CHICKEN;
        public static final MobType MOOSHROOM = AnimalTypes.MOOSHROOM;
        public static final MobType BAT = AnimalTypes.BAT;
        public static final MobType SQUID = AnimalTypes.SQUID;
        public static final MobType WOLF = AnimalTypes.WOLF;
        public static final MobType HORSE = AnimalTypes.HORSE;
        public static final MobType OCELOT = AnimalTypes.OCELOT;
        public static final MobType RABBIT = AnimalTypes.RABBIT;
        public static final MobType KILLER_RABBIT = AnimalTypes.KILLER_RABBIT;

        public static final MobType SNOW_GOLEM = GolemTypes.SNOW_GOLEM;
        public static final MobType IRON_GOLEM = GolemTypes.IRON_GOLEM;

        public static final MobType FARMER = VillagerTypes.FARMER;
        public static final MobType LIBRARIAN = VillagerTypes.LIBRARIAN;
        public static final MobType PRIEST = VillagerTypes.PRIEST;
        public static final MobType BLACKSMITH = VillagerTypes.BLACKSMITH;
        public static final MobType BUTCHER = VillagerTypes.BUTCHER;
    }

    /** This class is used to represent the different types of {@link Mob}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #FARMER farmers} and {@link #LIBRARIAN librarians} are both represented as separate types despite the fact that they both have the same I.D. value. */
    public static class MobType extends LivingEntityType {
        protected MobType(int id, int data) {
            super(id, data);

            addValueAs(MobType.class);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static MobType getByID(int id) {
            return getByID(MobType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static MobType getByID(int id, int data) {
            return getByID(MobType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static MobType[] values() {
            return values(MobType.class);
        }
    }
}
