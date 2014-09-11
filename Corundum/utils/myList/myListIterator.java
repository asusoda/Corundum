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

package Corundum.utils.myList;


import java.util.Iterator;
import java.util.NoSuchElementException;

/** This {@link Iterator} is able to iterate through {@link myList}s one element at a time from beginning to end. This class not only allows the use of {@link myLists}s in Java
 * for-each loops (e.g. <tt>for (Object object : objects)</tt>), but also provides a method of iterating through {@link myList}s much more efficient than retrieval by index;
 * because {@link myList}s are binary tree structures, every retrieval by index is <tt>Θ(lg(n))</tt>, but iterators allow searching from the last found node, which results in
 * <tt>Θ(1)</tt> time for iteration.
 * 
 * @author connor
 *
 * @param <T>
 *            is the type of element contained in the {@link myList} that this {@link myListIterator} is associated with. */
public class myListIterator<T> implements Iterator<T>, Cloneable {
    private myList<T> next;

    public myListIterator(myList<T> list) {
        if (list.isEmpty())
            next = null;
        else
            next = list.lowestValuedNode();
    }

    public myListIterator(myList<T> list, boolean start_from_lowest) {
        if (list == null || list.isEmpty())
            next = null;
        else if (start_from_lowest)
            next = list.lowestValuedNode();
        else
            next = list;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public T next() {
        if (next == null)
            throw new NoSuchElementException();

        T to_return = next.data();
        next = next.next();
        return to_return;
    }

    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public myListIterator<T> clone() {
        return new myListIterator<T>(next, false);
    }
}
