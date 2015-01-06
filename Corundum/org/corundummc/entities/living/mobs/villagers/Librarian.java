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
    }

    // instance utilities

    // overridden utilities
    @Override
    public LibrarianType getType() {
        return LibrarianType.TYPE;
    }
}