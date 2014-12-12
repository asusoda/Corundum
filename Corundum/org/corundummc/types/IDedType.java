package org.corundummc.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;
import org.corundummc.items.Item;
import org.corundummc.utils.interfaces.Matchable;
import org.corundummc.world.Block;
import org.corundummc.world.Block.BlockType;

/** This class represents anything that can be categorized into types with unique integer I.D. and data values.
 * 
 * @author REALDrummer
 *
 * @param <T>
 *            is a self-parameterization; <b><tt>T</b></tt> should be the same type as the type implementing this interface. This parameterization allows certain methods in
 *            this interface to return the appropriate subtype of {@link IDedType}. */
public abstract class IDedType<T extends IDedType<T>> implements Matchable<IDedType<T>> {
    protected static HashMap<Class<IDedType<?>>, IDedType<?>[]> values = new HashMap<>();

    /** <b><i>DEV NOTES:</b></i><br>
     * This isn't final because I couldn't find a way to initialize it statically in the constructors for {@link IDedTypeWithData} because of the type parameterization and the
     * way values are stored and accessed. */
    private short id;

    // constructors
    @SuppressWarnings("unchecked")
    protected IDedType() {
        IDedType<T>[] values = values(getClass());

        // if there are no previous values, default to 0
        if (values.length == 0)
            id = 0;
        // if there is a previous value, infer the I.D. from the previous value
        else
            id = (short) (values[values.length - 1].id + 1);

        addValueAs((Class<IDedType<?>>) getClass());
    }

    protected IDedType(int id) {
        this.id = (short) id;

        addValueAs((Class<IDedType<?>>) getClass());
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

    /** <b><i>DEV NOTES:</b</i><br>
     * This is necessary to allow {@link IDedTypeWithData} to modify {@link IDedType#id} without giving direct access to {@link IDedType#id} to extended types.
     * 
     * @param id
     *            is the new I.D. to set this to. (I know you already knew that; I made this param tag so Eclipse wouldn't give me errors.) */
    void setID(short id) {
        this.id = id;
    }

    public String getName() {
        // TODO
        return null;
    }

    protected IDedType<T> addValueAs(Class type_class) {
        T[] type_values = (T[]) values.get(type_class);

        ArrayList<T> new_type_values = new ArrayList<>(Arrays.asList((T) this));
        new_type_values.add((T) this);

        values.put((Class<IDedType<?>>) type_class, (IDedType<?>[]) new_type_values.toArray());

        return this;
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
