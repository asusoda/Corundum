package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;

import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;

public class Librarian extends Villager<Librarian, Librarian.LibrarianType> {
    public Librarian() {
        super(new EntityVillager(null));
    }

    protected Librarian(EntityVillager entityMC) {
        super(entityMC);
    }

    protected static class LibrarianType extends VillagerType<LibrarianType, Librarian> {
        public static final LibrarianType TYPE = new LibrarianType();

        private LibrarianType() {
            super(1);
        }

        // overridden utilities
        @Override
        public Librarian create() {
            return new Librarian();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Librarian fromMC(EntityVillager entityMC) {
            return new Librarian(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public LibrarianType getType() {
        return LibrarianType.TYPE;
    }
}