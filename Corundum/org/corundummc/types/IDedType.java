package org.corundummc.types;

import java.util.HashMap;

import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;
import org.corundummc.items.Item;
import org.corundummc.utils.interfaces.Matchable;
import org.corundummc.world.Block;
import org.corundummc.world.Block.BlockType;

/** This class represents anything that can be categorized into types with unique integer I.D. and data values.
 * 
 * @author REALDrummer
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class. */
public abstract class IDedType<S extends IDedType<S>> implements Matchable<S> {
    protected static HashMap<Class<? extends IDedType<?>>, IDedType<?>[]> values = new HashMap<>();

    /** <b><i>DEV NOTES:</b></i><br>
     * This isn't final because I couldn't find a way to initialize it statically in the constructors for {@link IDedTypeWithData} because of the type parameterization and the
     * way values are stored and accessed. */
    private short id;

    static {
        /* TODO: if in debugging mode, ensure that (outside of this types package),
         * 
         * 1) each subclass implements its own getByID(int) and values() methods,
         * 
         * 2) all the values of any given subclass has corresponding values with the same name in their parent class,
         * 
         * 3) each subclass has class type parameters matching those in this class in order and type */
    }

    // constructors
    protected IDedType(int id) {
        this.id = (short) id;
    }

    // static utilities
    protected static <R extends IDedType<R>> R getByID(Class<R> clazz, int id) {
        // TODO: replace this linear search with a binary search algorithm
        if (id >= 0)
            for (R type : values(clazz))
                if (type.getID() == id)
                    return type;
        return null;
    }

    @SuppressWarnings("unchecked")
    protected static <R extends IDedType<R>> R[] values(Class<R> clazz) {
        return (R[]) values.get(clazz);
    }

    protected static <R extends IDedType<R>> int nextID(Class<R> clazz) {
        if (values(clazz) == null)
            return 0;
        else
            return values(clazz)[values(clazz).length].getID() + 1;
    }

    // instance utilities
    /** This method returns the I.D. that represents this {@link IDedType}. In Minecraft, certain things such as {@link Item items} and {@link Block blocks} can be categorized
     * into "types" that are represented by different I.D.s. You can see which I.D.s represent which blocks or items on <a href=http://minecraft-ids.grahamedgecombe.com/>this
     * site</a>.
     * <hr>
     * <i>NOTE:</i> Just like in Minecraft's code, not all Corundum {@link IDedType}s have different I.D.s; some things, like {@link BlockType#STONE stone} and
     * {@link BlockType#GRANITE granite}, are {@link IDedTypeWithData}, which means they can have the same I.D. and are differentiated by their data values. For example,
     * {@link BlockType#STONE stone} and {@link BlockType#GRANITE granite} both have the I.D. 1, but {@link BlockType#STONE stone} has a data value of 0 while
     * {@link BlockType#GRANITE granite} has a data value of 1. Corundum refers to {@link IDedType}s with the same I.D. but different data values as "siblings".
     * 
     * @return the I.D. associated with this {@link IDedType} or -1 if this type is an "unsaved entity" that has no I.D. such as a {@link ProjectileType#EGG thrown egg}. */
    public short getID() {
        return id;
    }

    public String getName() {
        // TODO
        return null;
    }

    @SuppressWarnings("unchecked")
    protected void addValueAs(Class<?> type_class) {
        IDedType<?>[] type_values = values.get(type_class), new_type_values = new IDedType[type_values.length + 1];

        for (int i = 0; i < type_values.length; i++)
            new_type_values[i] = type_values[i];
        new_type_values[new_type_values.length - 1] = this;

        values.put((Class<IDedType<?>>) type_class, new_type_values);
    }

    // data management overrides
    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getID(), getName() };
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.getID() + ")";
    }
}
