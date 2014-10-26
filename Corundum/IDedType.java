package Corundum;

/** This interface is meant to represent anything that can be categorized into types with unique integer I.D. and data values.
 * 
 * @author REALDrummer
 *
 * @param <T>
 *            is a self-parameterization; <b><tt>T</b></tt> should be the same type as the type implementing this interface. This parameterization allows certain methods in
 *            this interface to return the appropriate subtype of {@link IDedType}. */
public interface IDedType<T> {
    /** This method returns the I.D. that represents this {@link IDedType}. In Minecraft, every type of block is represented by a different I.D. You can see which I.D.s
     * represent which blocks on <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
     * <hr>
     * <i>NOTE:</i> Not all Corundum {@link IDedType}s have different I.D.s; some things, like {@link IDedType#STONE stone} and {@link IDedType#GRANITE granite}, have the same
     * I.D. and are differentiated by their data values (in this case, 0 and 1, respectively).
     * 
     * @return the block I.D. associated with this {@link IDedType}. */
    public short getID();
}
