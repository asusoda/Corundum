/** This code is property of the Corundum project managed under the Software Developers' Association of Arizona State University.
 *
 * Copying and use of this open-source code is permitted provided that the following requirements are met:
 *
 * - This code may not be used or distributed for private enterprise, including but not limited to personal or corporate profit. - Any products resulting from the copying,
 * use, or modification of this code may not claim endorsement by the Corundum project or any of its members or associates without written formal permission from the endorsing
 * party or parties. - This code may not be copied or used under any circumstances without the inclusion of this notice and mention of the contribution of the code by the
 * Corundum project. In source code form, this notice must be included as a comment as it is here; in binary form, proper documentation must be included with the final product
 * that includes this statement verbatim.
 *
 * @author REALDrummer */

package org.corundummc.biomes;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.utils.interfaces.CreatableType;
import org.corundummc.utils.interfaces.MCEquivalent;
import org.corundummc.utils.interfaces.MCEquivalentType;
import org.corundummc.utils.types.IDedType;
import org.corundummc.utils.types.Typed;
import org.corundummc.world.Location;

import net.minecraft.entity.EntityList;
import net.minecraft.world.biome.BiomeGenBase;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class Biome<S extends Biome<S, MC, T>, MC extends BiomeGenBase, T extends Biome.BiomeType<T, MC, S>> extends Typed<T> {
    /** Minecraft has no Biome object; the best way I thought to represent a Biome without using lots of C.P.U. and memory is to just store the location of one block in the
     * biome; this could allow us to calculate whether or not other points are in this same biome and other properties will likely come from the biome's type */
    private Location location;

    protected Biome(Location location) {
        this.location = location;
    }

    public static interface BiomeTypes {
        // TODO
    }

    public abstract static class BiomeType<S extends BiomeType<S, MC, I>, MC extends BiomeGenBase, I extends Biome<I, MC, S>> extends IDedType<S> implements
            MCEquivalentType<MC, I> {
        protected BiomeType(int id) {
            super(id);

            addValueAs(BiomeType.class);
        }

        // abstract utilities
        public abstract I fromLocation(Location location);

        // overridden utilities

        // static utilities
        /** <b><u>This method may be ignored by plugin developers; it is not likely to be of use to you.</b></u><br>
         * This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link net.minecraft.entity.Entity Minecraft Entity}.
         * 
         * @param biomeMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @SuppressWarnings("unchecked")
        public static <MC extends BiomeGenBase> Biome<?, MC, ?> fromMC(MC biomeMC) {
            return ((BiomeType<?, MC, ?>) BiomeType.getByID(biomeMC.biomeID)).fromMC(biomeMC);
        }

        // pseudo-enum utilities
        public static BiomeType<?, ?, ?> getByID(int id) {
            return getByID(BiomeType.class, id);
        }

        public static BiomeType<?, ?, ?>[] values() {
            return values(BiomeType.class);
        }
    }

    // static utilities
    public static Biome<?, ?, ?> fromLocation(Location location) {
        return BiomeType.getByID(location.getChunk().MC().getBiomeArray()[location.getBlockX() * 16 + location.getBlockZ()]).fromLocation(location);
    }

    // type utilities

    // instance utilities
}
