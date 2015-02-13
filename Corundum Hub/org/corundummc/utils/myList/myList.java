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

package org.corundummc.utils.myList;

import java.util.ArrayList;
import java.util.Collection;

import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.MatchUtilities;
import org.corundummc.utils.interfaces.Matchable;

import static org.corundummc.utils.ListUtilities.*;
import static org.corundummc.utils.MatchUtilities.match;

/** This list structure is an auto-sorting quick-searching structure based on "root-knockdown" auto-balancing binary trees.
 * <hr>
 * A <tt>myList</tt> can store and sort any type of <tt>Object</tt>, but it is <i>highly</i> recommended that the <tt>Object</tt> stored in this list is both
 * {@link Comparable} and {@link Matchable}. If the <tt>Object</tt> is not {@link Comparable}, the <tt>myList</tt> will compare the outputs of the <tt>Object</tt>s'
 * <tt>toString()</tt> methods; however, this can lead to complications in searching for an item in the list using {@link #find(Object)} if the <tt>Object</tt> that you are
 * searching for does not have the same <tt>toString()</tt> output as the <tt>Object</tt> given to search for. If the <tt>Object</tt> is not {@link Matchable},
 * {@link #findMatch(Object...) myList's findMatch() methods} will throw errors.
 * 
 * @author REALDrummer
 * 
 * @param <T>
 *            is the type of <tt>Object</tt> which the <tt>myList</tt> will hold. <i>Please read the description above for notes on the dangers of not using {@link Comparable}
 *            {@link Matchable} <tt>Object</tt>s.</i> */
@SuppressWarnings("unchecked")
public class myList<T> implements Comparable<T>, Cloneable, Iterable<T>, Matchable<myList<T>> {
    private T data = null;
    private myList<T> left = null, right = null, root = null;
    private Integer size = 0;  // I used Integer instead of int so that it can be set to null and garbage collected in free()

    // constructors
    /** This constructor creates a new <tt>myList</tt>, an efficient auto-sorting quick-searching list structure based on "root-knockdown" auto-balancing binary trees.
     * 
     * @param objects
     *            is the (optional) list of objects to add to the new <tt>myList</tt>. */
    public myList(T... objects) {
        add(objects);
    }

    /** This constructor creates a new <tt>myList</tt>, an efficient auto-sorting quick-searching list structure based on "root-knockdown" auto-balancing binary trees.
     * 
     * @param objects
     *            is the (optional) list of objects to add to the new <tt>myList</tt>. */
    public myList(Collection<T> objects) {
        add(objects);
    }

    /** This constructor creates a new <tt>myList</tt>, an efficient auto-sorting quick-searching list structure based on "root-knockdown" auto-balancing binary trees.
     * 
     * @param objects
     *            is the (optional) list of objects to add to the new <tt>myList</tt>. */
    public myList(myList<T> objects) {
        add(objects);
    }

    // private recursive methods
    private int _add(T object, int current_index) {
        size++;

        // see whether the element should be added to the left or right side
        if (compareTo(object) <= 0)
            // if the right side is open, just add object to the right side
            if (!hasRight() || !right.isFull() || hasLeft() && left.isFull() && right.length() <= left.length())
                return addRight(object, current_index);
            // if the right side is taken, use root knockdown to rearrange the child branches
            else {
                // knock-down data-shuffle from the right to the left side
                addLeft(data, current_index);
                current_index++; // add 1 to the current index since we added an element to the left side of the list
                myList<T> lowest_right = right.lowestValuedNode();

                // if the lowest_right's data is lower than object, use lowest_right for the knock-down
                if (lowest_right.compareTo(object) < 0) {
                    data = lowest_right.data;
                    lowest_right.remove(this);
                    return addRight(object, current_index);
                } // if the object is lower than lowest_right's data, use object for the knock-down
                else {
                    data = object;
                    return current_index;
                }
            }
        else {
            // if the left side is open, just add object to the left side
            if (!hasLeft() || !left.isFull() || hasRight() && right.isFull() && left.length() <= right.length())
                return addLeft(object, current_index);
            // if the left side is taken, use root knockdown to rearrange the child branches
            else {
                // knock-down data-shuffle from the left to the right side
                addRight(data, current_index);
                myList<T> highest_left = left.highestValuedNode();

                // if the highest_left's data is higher than object, use highest_left for the knock-down
                if (highest_left.compareTo(object) > 0) {
                    data = highest_left.data;
                    highest_left.remove(this);
                    return addLeft(object, current_index);
                } // if the object is higher than highest_right's data, use object for the knock-down
                else {
                    data = object;
                    return current_index;
                }
            }
        }
    }

    private int addLeft(T object, int current_index) {
        if (hasLeft()) {
            return left._add(object, current_index - (left.hasRight() ? left.right.length() : 0) - 1);
        } else {
            left = new myList<T>(object);
            left.root = this;
            return current_index;
        }
    }

    private int addRight(T object, int current_index) {
        if (hasRight())
            return right._add(object, current_index + (right.hasLeft() ? right.left.length() : 0) + 1);
        else {
            right = new myList<T>(object);
            right.root = this;
            return current_index + 1;
        }
    }

    private ArrayList<String> debugDraw(ArrayList<String> result, int level) {
        // if needed, add a new String for the new level to the result list
        if (level >= result.size())
            result.add("");

        result.set(level, result.get(level) + data + size + " ");

        if (hasLeft())
            left.debugDraw(result, level + 1);
        else {
            if (level + 1 >= result.size())
                result.add("");

            result.set(level + 1, result.get(level + 1) + "\"\" ");
        }

        if (hasRight())
            right.debugDraw(result, level + 1);
        else {
            if (level + 1 >= result.size())
                result.add("");

            result.set(level + 1, result.get(level + 1) + "\"\" ");
        }

        return result;
    }

    private int find(T object, int current_index) {
        if (compareTo(object) == 0) {
            // find the lowest index value of that item
            int lower_index = -1;
            if (hasLeft())
                lower_index = left.find(object, current_index - 1 - (left.hasRight() ? left.right.length() : 0));

            // return the lowest index if there was a lower index or this index otherwise (adjusted for the left)
            if (lower_index == -1)
                return current_index;
            else
                return lower_index;
        } else if (compareTo(object) <= 0)
            if (hasRight())
                return right.find(object, current_index + 1 + (right.hasLeft() ? right.left.length() : 0));
            else
                return -1;
        else if (hasLeft())
            return left.find(object, current_index - 1 - (left.hasRight() ? left.right.length() : 0));
        else
            return -1;
    }

    private int findMatchIndex(Object[] match_parameters, int current_index) {
        if (match(data, match_parameters) == 0) {
            // find the lowest index value of that item
            int lower_index = -1;
            if (hasLeft())
                lower_index = left.findMatchIndex(match_parameters, current_index - 1 - (left.hasRight() ? left.right.length() : 0));

            // return the lowest index if there was a lower index or this index otherwise
            if (lower_index == -1)
                return current_index;
            else
                return lower_index;
        } else if (match(data, match_parameters) <= 0)
            if (hasRight())
                return right.findMatchIndex(match_parameters, current_index + 1 + (right.hasLeft() ? right.left.length() : 0));
            else
                return -1;
        else if (hasLeft())
            return left.findMatchIndex(match_parameters, current_index - 1 - (left.hasRight() ? left.right.length() : 0));
        else
            return -1;
    }

    /** This method removes this node and all its children from the list and adjusts the sizes of the parents according to the number of nodes removed and the <b>
     * <tt>adjust_size_stopper</b></tt>.
     * 
     * @param adjust_size_stopper
     *            is a {@link myList} node that will stop the part of this method that adjusts the size of the parents according to the number of nodes lost. When this method
     *            removes a node or nodes from the tree, it uses a loop to adjust the sizes of all parents of this node to account for the removed nodes up to (but <i>not</i>
     *            including) the <b><tt>adjust_size_stopper</b></tt>. If <b><tt>adjust_size_stopper</b></tt> is <b>null</b> or not a parent of this {@link myList} node, the
     *            size of every parent will be adjusted. */
    private void free(myList<T> adjust_size_stopper) {
        if (isEmpty())
            return;

        // save the original size of this node for adjusting the size of its parents
        int old_size = size.intValue();

        /* free the left and the right of this list first; tell the free() calls here to not adjust the sizes of the parents because we will do that later in this method all
         * at once for all the nodes instead of a bunch of times each time a child is removed, which is much more efficient if we're freeing a node with children */
        if (hasLeft())
            left.free(this /* do not adjust the size for the removal for any nodes */);
        if (hasRight())
            right.free(this/* do not adjust the size for the removal for any nodes */);

        /* if adjust_sizes is true, go up through all of this node's parents and adjust their heights to reflect the removal of this node; adjust_sizes may be false to save
         * operations in this method when the children are freed */
        myList<T> parent = root;
        while (parent != null && parent != adjust_size_stopper) {
            parent.size -= old_size;
            parent = parent.root;
        }

        // remove the root's pointer that points to this node
        if (hasRoot())
            if (isLeft())
                root.left = null;
            else
                root.right = null;

        // set the contents to null and leave it to the garbage collector
        data = null;
        left = null;
        right = null;

        // if this is the root of the whole list, set its size to 0
        if (!hasRoot())
            size = 0;
        // if this is not the root of the whole list, set the root and size to null for garbage collection
        else {
            size = null;
            root = null;
        }
    }

    private myList<T> getNode(int index, int current_index) {
        int left_length = hasLeft() ? left.length() : 0;
        if (left_length + current_index > index)
            return left.getNode(index, current_index);
        else if (left_length + current_index == index)
            return this;
        else if (!hasRight())
            return null;
        else
            return right.getNode(index, current_index + left_length + 1);
    }

    private int lastIndexOf(T object, int current_index) {
        if (compareTo(object) == 0) {
            // f// find the highest index value of that item
            int higher_index = -1;
            if (hasRight())
                higher_index = right.lastIndexOf(object, current_index + 1 + (right.hasLeft() ? right.left.length() : 0));

            // return the highest index if there was a higher index or this index otherwise (adjusted for the left)
            if (higher_index == -1)
                return current_index;
            else
                return higher_index;
        } else if (compareTo(object) > 0)
            if (hasRight())
                return right.find(object, current_index + 1 + (right.hasLeft() ? right.left.length() : 0));
            else
                return -1;
        else if (hasLeft())
            return left.find(object, current_index - 1 - (left.hasRight() ? right.length() : 0));
        else
            return -1;
    }

    private int lastIndexOfMatch(Object[] match_parameters, int current_index) {
        if (match(data, match_parameters) == 0) {
            // find the highest index value of that item
            int higher_index = -1;
            if (hasRight())
                higher_index = right.lastIndexOfMatch(match_parameters, current_index + 1 + (right.hasLeft() ? right.left.length() : 0));

            // return the highest index if there was a higher index or this index otherwise (adjusted for the left)
            if (higher_index == -1)
                return current_index;
            else
                return higher_index;
        } else if (match(data, match_parameters) > 0)
            if (hasRight())
                return right.findMatchIndex(match_parameters, current_index + 1 + (right.hasLeft() ? right.left.length() : 0));
            else
                return -1;
        else if (hasLeft())
            return left.findMatchIndex(match_parameters, current_index - 1 - (left.hasRight() ? right.length() : 0));
        else
            return -1;
    }

    private void remove(myList<T> adjust_size_stopper) {
        // if the node has a left that's equal to or longer than the right, shift the left list's highest node to the root and delete the old left list's highest node
        if (hasLeft() && (!hasRight() || right.length() <= left.length())) {
            myList<T> highest_left = left.highestValuedNode();
            data = highest_left.data;
            highest_left.remove();
        } // if the node has a right that's longer than the left, shift the right list's lowest node to the root and delete the old right list's lowest node
        else if (hasRight()) {
            myList<T> lowest_right = right.lowestValuedNode();
            data = lowest_right.data;
            lowest_right.remove();
        } // if the node is a leaf, just delete its contents and leave it at that
        else
            free(adjust_size_stopper);
    }

    private myList<T> sublist(int begin_index, int end_index, int current_index) {
        // first, search through the tree until we find the first element whose index is within the bounds; this element will be the root of the sublist
        myList<T> clone = this;
        while (begin_index > current_index || end_index < current_index)
            if (begin_index > current_index && right != null) {
                clone = clone.right;
                current_index += 1 + (clone.hasLeft() ? left.length() : 0);
            } else if (end_index < current_index && left != null) {
                clone = clone.left;
                current_index -= 1 + (clone.hasRight() ? right.length() : 0);
            } else
                return null;

        // once we have found the root of the sublist, add that element to the new list and check the left and right for additional elements
        if (hasLeft()) {
            myList<T> left_sublist = left.sublist(begin_index, end_index, current_index - (left.hasRight() ? left.right.length() : 0) - 1);
            if (left_sublist != null)
                clone.add(left_sublist);
        }
        if (hasRight()) {
            myList<T> right_sublist = right.sublist(begin_index, end_index, current_index + (right.hasLeft() ? right.left.length() : 0) + 1);
            if (right_sublist != null)
                clone.add(right_sublist);
        }

        return clone;
    }

    // public methods
    /** This method adds the given <tt>Object</tt> to the {@link myList}.
     * 
     * @param object
     *            is the <tt>Object</tt> to add to the {@link myList}.
     * @return the index at which <b><tt>object</b></tt> was added.
     * @see {@link #add(Object...)}, {@link #add(Collection)}, and {@link #add(myList)}. */
    public int add(T object) {
        if (isEmpty()) {
            data = object;
            size = 1;
            return 0;
        } else
            return _add(object, hasLeft() ? left.length() : 0);
    }

    /** This method adds the given <tt>Object</tt>s to the {@link myList}.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s to add to the {@link myList}.
     * @return a list of indices at which the given <tt>Object</tt>s were added.
     * @see {@link #add(Object)}, {@link #add(Collection)}, and {@link #add(myList)}. */
    public int[] add(T... objects) {
        if (objects.length == 0)
            return new int[0];

        int[] indices = new int[objects.length];
        for (int i = 0; i < objects.length; i++) {
            indices[i] = add(objects[i]);
            /* add 1 to all indices greater than index already in the indices list to account for the fact that adding an element to the list shifts the indices of every
             * element after it in the list */
            for (int j = 0; j < i; j++)
                if (indices[j] >= indices[i])
                    indices[j]++;
        }

        return indices;
    }

    /** This method adds the given <tt>Object</tt>s to the {@link myList}.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s to add to the {@link myList}.
     * @return a list of indices at which the given <tt>Object</tt>s were added.
     * @see {@link #add(Object)}, {@link #add(Object)}, and {@link #add(myList)}. */
    public int[] add(Collection<T> objects) {
        return add((T[]) objects.toArray());
    }

    /** This method adds the given <tt>Object</tt>s to the {@link myList}.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s to add to the {@link myList}.
     * @return a list of indices at which the given <tt>Object</tt>s were added.
     * @see {@link #add(Object)}, {@link #add(Object)}, and {@link #add(Collection)}. */
    public int[] add(myList<T> objects) {
        return add(objects.toArray());
    }

    /** This method removes every element in the {@link myList}.
     * 
     * @see {@link #delete()}, {@link #free()}, and {@link #remove()}. */
    public void clear() {
        free();
    }

    /** This method determines whether or not the {@link myList} contains the given <tt>Object</tt>.
     * 
     * @param object
     *            is the <tt>Object</tt> that this method will search for in the {@link myList}.
     * @return <b>true</b> if <b><tt>object</b></tt> is inside the {@link myList}; <b>false</b> otherwise.
     * @see {@link #contains(Object...)}, {@link #contains(Collection)}, {@link #contains(myList)}, {@link #containsAND(Object...)}, and {@link #containsOR(Object...)}. */
    public boolean contains(T object) {
        return find(object) != -1;
    }

    /** This method determines whether or not the {@link myList} contains the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return a <b>boolean</b><tt>[]</tt> in which each <b>boolean</b> is <b>true</b> if the <tt>Object</tt> at the corresponding index in the given <b><tt>objects</b></tt>
     *         list is inside the {@link myList} or <b>false</b> otherwise.
     * @see {@link #contains(Object)}, {@link #contains(Collection)}, {@link #contains(myList)}, {@link #containsAND(Object...)}, and {@link #containsOR(Object...)}. */
    public boolean[] contains(T... objects) {
        boolean[] results = new boolean[objects.length];

        for (int i = 0; i < objects.length; i++)
            results[i] = contains(objects[i]);

        return results;
    }

    /** This method determines whether or not the {@link myList} contains the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return a <b>boolean</b><tt>[]</tt> in which each <b>boolean</b> is <b>true</b> if the <tt>Object</tt> at the corresponding index in the given <b><tt>objects</b></tt>
     *         list is inside the {@link myList} or <b>false</b> otherwise.
     * @see {@link #contains(Object)}, {@link #contains(Object...)}, {@link #contains(myList)}, {@link #containsAND(Object...)}, and {@link #containsOR(Object...)}. */
    public boolean[] contains(Collection<T> objects) {
        boolean[] results = new boolean[objects.size()];

        int i = 0;
        for (T object : objects) {
            results[i] = contains(object);
            i++;
        }

        return results;
    }

    /** This method determines whether or not the {@link myList} contains the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return a <b>boolean</b><tt>[]</tt> in which each <b>boolean</b> is <b>true</b> if the <tt>Object</tt> at the corresponding index in the given <b><tt>objects</b></tt>
     *         list is inside the {@link myList} or <b>false</b> otherwise.
     * @see {@link #contains(Object)}, {@link #contains(Object...)}, {@link #contains(Collection)}, {@link #containsAND(Object...)}, and {@link #containsOR(Object...)}. */
    public boolean[] contains(myList<T> objects) {
        boolean[] results = new boolean[objects.size()];

        int i = 0;
        for (T object : objects) {
            results[i] = contains(object);
            i++;
        }

        return results;
    }

    /** This method determines whether or not the {@link myList} contains <i>all</i> of the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return <b>true</b> if the {@link myList} contains <i>all</i> of the given <tt>Objects</tt>; <b>false</b> otherwise.
     * @see {@link #containsAND(Collection)}, {@link #containsAND(myList)}, {@link #contains(Object)}, and {@link #containsOR(Object...)}. */
    public boolean containsAND(T... objects) {
        for (T object : objects)
            if (!contains(object))
                return false;
        return true;
    }

    /** This method determines whether or not the {@link myList} contains <i>all</i> of the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return <b>true</b> if the {@link myList} contains <i>all</i> of the given <tt>Objects</tt>; <b>false</b> otherwise.
     * @see {@link #containsAND(Object...)}, {@link #containsAND(myList)}, {@link #contains(Object)}, and {@link #containsOR(Object...)}. */
    public boolean containsAND(Collection<T> objects) {
        for (T object : objects)
            if (!contains(object))
                return false;
        return true;
    }

    /** This method determines whether or not the {@link myList} contains <i>all</i> of the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return <b>true</b> if the {@link myList} contains <i>all</i> of the given <tt>Objects</tt>; <b>false</b> otherwise.
     * @see {@link #containsAND(Object...)}, {@link #containsAND(Collection)}, {@link #contains(Object)}, and {@link #containsOR(Object...)}. */
    public boolean containsAND(myList<T> objects) {
        for (T object : objects)
            if (!contains(object))
                return false;
        return true;
    }

    /** This method determines whether or not the {@link myList} contains <i>at least one</i> of the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return <b>true</b> if the {@link myList} contains <i>at least one</i> of the given <tt>Objects</tt>; <b>false</b> otherwise.
     * @see {@link #containsOR(Collection)}, {@link #containsOR(myList)}, {@link #contains(Object)}, and {@link #containsAND(Object...)}. */
    public boolean containsOR(T... objects) {
        for (T object : objects)
            if (contains(object))
                return true;
        return false;
    }

    /** This method determines whether or not the {@link myList} contains <i>at least one</i> of the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return <b>true</b> if the {@link myList} contains <i>at least one</i> of the given <tt>Objects</tt>; <b>false</b> otherwise.
     * @see {@link #containsOR(Object...)}, {@link #containsOR(myList)}, {@link #contains(Object)}, and {@link #containsAND(Object...)}. */
    public boolean containsOR(Collection<T> objects) {
        for (T object : objects)
            if (contains(object))
                return true;
        return false;
    }

    /** This method determines whether or not the {@link myList} contains <i>at least one</i> of the given <tt>Object</tt>s.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return <b>true</b> if the {@link myList} contains <i>at least one</i> of the given <tt>Objects</tt>; <b>false</b> otherwise.
     * @see {@link #containsOR(Object...)}, {@link #containsOR(Collection)}, {@link #contains(Object)}, and {@link #containsAND(Object...)}. */
    public boolean containsOR(myList<T> objects) {
        for (T object : objects)
            if (contains(object))
                return true;
        return false;
    }

    /** This method returns the <tt>Object</tt> residing in this particular <i>node</i> of the {@link myList}. This method was made public for use in {@link myListIterator} and
     * <i>is not recommended for general use</i>.
     * 
     * @return the <tt>Object</tt> residing in this particular <i>node</i> of the {@link myList}. */
    public T data() {
        return data;
    }

    /** This method removes every element in the {@link myList}.
     * 
     * @see {@link #clear()}, {@link #free()}, and {@link #remove()}. */
    public void delete() {
        free();
    }

    /** This method gathers a bunch of debugging information concerning the {@link myList} and returns it as a <tt>String</tt>.
     * 
     * @return a <tt>String</tt> containing plenty of debugigng information concerning this {@link myList}. */
    public String debug() {
        return String.valueOf(length()) + (hasLeft() ? "; " + left.length() + "l" : "") + (hasRight() ? "; " + right.length() + "r" : "") + (hasRoot() ? "; has root!" : "")
                + "\n" + toString();
    }

    public void debugFull() {
        if (hasLeft())
            left.debugFull();

        debug();

        if (hasRight())
            right.debugFull();
    }

    public void debugDraw() {
        if (isEmpty())
            System.out.println("\n\"\"");
        else
            System.out.println("\n" + writeArrayList(debugDraw(new ArrayList<String>(), 0), "\n", "\n"));
    }

    /** This method finds the given <tt>Object</tt> and returns its index in the {@link myList}.
     * 
     * @param object
     *            is the <tt>Object</tt> that this method will search for in the {@link myList}.
     * @return the index of <b><tt>object</b></tt> in the {@link myList} or -1 if <b><tt>object</b></tt> is not in the {@link myList}.
     * @see {@link #find(Object...)}, {@link #find(Collection)}, {@link #find(myList)}, {@link #get(Object)}, and {@link #findMatch(Object...)}. */
    public int find(T object) {
        return find(object, hasLeft() ? left.length() : 0);
    }

    /** This method finds the given <tt>Object</tt>s and returns their indices in the {@link myList}.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return a <b>int</b><tt>[]</tt> in which each <b>int</b> is the index in the {@link myList} of the <tt>Object</tt> at the corresponding index in the given <b>
     *         <tt>objects</b></tt> list or -1 if <b><tt>object</b></tt> is not in the {@link myList}.
     * @see {@link #find(Object)}, {@link #find(Collection)}, {@link #find(myList)}, {@link #get(Object)}, and {@link #findMatch(Object...)}. */
    public int[] find(T... objects) {
        int[] indices = new int[objects.length];
        for (int i = 0; i < objects.length; i++)
            indices[i] = find(objects[i]);
        return indices;
    }

    /** This method finds the given <tt>Object</tt>s and returns their indices in the {@link myList}.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return a <b>int</b><tt>[]</tt> in which each <b>int</b> is the index in the {@link myList} of the <tt>Object</tt> at the corresponding index in the given <b>
     *         <tt>objects</b></tt> list or -1 if <b><tt>object</b></tt> is not in the {@link myList}.
     * @see {@link #find(Object)}, {@link #find(Object...)}, {@link #find(myList)}, {@link #get(Object)}, and {@link #findMatch(Object...)}. */
    public int[] find(Collection<T> objects) {
        return find((T[]) objects.toArray());
    }

    /** This method finds the given <tt>Object</tt>s and returns their indices in the {@link myList}.
     * 
     * @param objects
     *            is the list of <tt>Object</tt>s that this method will search for in the {@link myList}.
     * @return a <b>int</b><tt>[]</tt> in which each <b>int</b> is the index in the {@link myList} of the <tt>Object</tt> at the corresponding index in the given <b>
     *         <tt>objects</b></tt> list or -1 if <b><tt>object</b></tt> is not in the {@link myList}.
     * @see {@link #find(Object)}, {@link #find(Object...)}, {@link #find(Collection)}, {@link #get(Object)}, and {@link #findMatch(Object...)}. */
    public int[] find(myList<T> objects) {
        return find(objects.toArray());
    }

    public T findLastMatch(Object... match_parameters) {
        myList<T> node = findLastMatchingNode(match_parameters);
        if (node == null)
            return null;
        else
            return node.data;
    }

    public T[] findLastMatches(Object... match_parameter_sets) {
        T[] matches = (T[]) new Object[match_parameter_sets.length];
        for (int i = 0; i < match_parameter_sets.length; i++)
            matches[i] = findLastMatch(match_parameter_sets[i]);
        return matches;
    }

    public T[] findLastMatches(Collection<Object[]> match_parameter_sets) {
        return findLastMatches(match_parameter_sets.toArray());
    }

    public T[] findLastMatches(myList<Object[]> match_parameter_sets) {
        return findLastMatches(match_parameter_sets.toArray());
    }

    public myList<T> findLastMatchingNode(Object... match_parameters) {
        if (match(data, match_parameters) == 0) {
            // find the highest index value of that item
            myList<T> higher_match = null;
            if (hasLeft())
                higher_match = left.findLastMatchingNode(match_parameters);

            // return the lowest index if there was a lower index or this index otherwise (adjusted for the left)
            if (higher_match == null)
                return this;
            else
                return higher_match;
        } else if (match(data, match_parameters) <= 0)
            if (hasRight())
                return right.findMatchingNode(match_parameters);
            else
                return null;
        else if (hasLeft())
            return left.findMatchingNode(match_parameters);
        else
            return null;
    }

    /** This method finds the <tt>Object</tt> in the {@link myList} that matches the given <b><tt>match_parameters</b></tt> using the <tt>Object</tt>'s
     * {@link Matchable#matchTo(Object...)} method.
     * <hr>
     * Note that this method only works for <tt>Object</tt>s that implement {@link Matchable the Matchable interface}. If the {@link myList} does not contain {@link Matchable}
     * <tt>Object</tt>s, this method will throw an error.
     * <hr>
     * Also, this method will not function properly if the parameters for the <tt>Object</tt>'s {@link Matchable#matchTo(Object...)} method work differently than the
     * parameters for the <tt>Object</tt>'s {@link Comparable#compareTo(Object)} method (assuming the <tt>Object</tt> is {@link Comparable}).
     * 
     * @param match_parameters
     *            is the list of <tt>String</tt>s that will be given as the argument when {@link Matchable#matchTo(Object...)} is called while searching the {@link myList}.
     * @return the <tt>Object</tt> that {@link Matchable#matchTo(Object...) matches} the given <b><tt>match_parameters</b></tt> in the {@link myList} or <b>null</b> if no
     *         <tt>Object</tt> in the {@link myList} matches the given <b><tt>match_parameters</b></tt>. */
    public T findMatch(Object... match_parameters) {
        myList<T> node = findMatchingNode(match_parameters);
        if (node == null)
            return null;
        else
            return node.data;
    }

    public T[] findMatches(Object[]... match_parameter_sets) {
        T[] matches = (T[]) new Object[match_parameter_sets.length];
        for (int i = 0; i < match_parameter_sets.length; i++)
            matches[i] = findMatch(match_parameter_sets[i]);
        return matches;
    }

    public T[] findMatches(Collection<Object[]> match_parameter_sets) {
        return findMatches((String[][]) match_parameter_sets.toArray());
    }

    public T[] findMatches(myList<Object[]> match_parameter_sets) {
        return findMatches(match_parameter_sets.toArray());
    }

    public int findMatchIndex(Object... match_parameters) {
        return findMatchIndex(match_parameters, hasLeft() ? left.length() : 0);
    }

    public int[] findMatchIndices(Object[]... match_parameter_sets) {
        int[] matches = new int[match_parameter_sets.length];
        for (int i = 0; i < match_parameter_sets.length; i++)
            matches[i] = findMatchIndex(match_parameter_sets[i]);
        return matches;
    }

    public int[] findMatchIndices(Collection<Object[]> match_parameter_sets) {
        return findMatchIndices((String[][]) match_parameter_sets.toArray());
    }

    public int[] findMatchIndices(myList<Object[]> match_parameter_sets) {
        return findMatchIndices(match_parameter_sets.toArray());
    }

    public myList<T> findMatchingNode(Object... match_parameters) {
        if (match(data, match_parameters) == 0) {
            // find the lowest index value of that item
            myList<T> lower_match = null;
            if (hasLeft())
                lower_match = left.findMatchingNode(match_parameters);

            // return the lowest index if there was a lower index or this index otherwise (adjusted for the left)
            if (lower_match == null)
                return this;
            else
                return lower_match;
        } else if (match(data, match_parameters) <= 0)
            if (hasRight())
                return right.findMatchingNode(match_parameters);
            else
                return null;
        else if (hasLeft())
            return left.findMatchingNode(match_parameters);
        else
            return null;
    }

    public myList<T>[] findMatchingNodes(Object[]... match_parameter_sets) {
        myList<T>[] matches = (myList<T>[]) new Object[match_parameter_sets.length];
        for (int i = 0; i < match_parameter_sets.length; i++)
            matches[i] = findMatchingNode(match_parameter_sets[i]);
        return matches;
    }

    public myList<T>[] findMatchingNodes(Collection<Object[]> match_parameter_sets) {
        return findMatchingNodes((String[][]) match_parameter_sets.toArray());
    }

    public myList<T>[] findMatchingNodes(myList<Object[]> match_parameter_sets) {
        return findMatchingNodes(match_parameter_sets.toArray());
    }

    public myList<T> findNode(T object) {
        if (compareTo(object) == 0) {
            // find the lowest index value of that item
            myList<T> lower_indexed_node = null;
            if (hasLeft())
                lower_indexed_node = left.findNode(object);

            // return the lowest indexed node if there was one with a lower index or this node otherwise
            if (lower_indexed_node == null)
                return this;
            else
                return lower_indexed_node;
        } else if (compareTo(object) <= 0)
            if (hasRight())
                return right.findNode(object);
            else
                return null;
        else if (hasLeft())
            return left.findNode(object);
        else
            return null;
    }

    public myList<T>[] findNodes(T... objects) {
        myList<T>[] nodes = new myList[objects.length];
        for (int i = 0; i < objects.length; i++)
            nodes[i] = findNode(objects[i]);
        return nodes;
    }

    public myList<T>[] findNodes(Collection<T> objects) {
        return findNodes((T[]) objects.toArray());
    }

    public myList<T>[] findNodes(myList<T> objects) {
        return findNodes(objects.toArray());
    }

    public void free() {
        free(null);
    }

    public T get(int index) {
        myList<T> node = getNode(index, 0);

        if (node == null)
            return null;
        else
            return node.data;
    }

    public T[] get(int... indices) {
        Object[] results = new Object[indices.length];
        for (int i = 0; i < indices.length; i++)
            results[i] = get(indices[i]);
        return (T[]) results;
    }

    public int get(T object) {
        return find(object);
    }

    public int[] get(T... objects) {
        return find(objects);
    }

    public int[] get(Collection<T> objects) {
        return find(objects);
    }

    public int[] get(myList<T> objects) {
        return find(objects);
    }

    public T getMatch(Object... match_parameters) {
        return findMatch(match_parameters);
    }

    public T[] getMatch(Object[]... match_parameter_sets) {
        return findMatches(match_parameter_sets);
    }

    public T[] getMatch(Collection<Object[]> match_parameter_sets) {
        return findMatches(match_parameter_sets);
    }

    public T[] getMatch(myList<Object[]> match_parameter_sets) {
        return findMatches(match_parameter_sets);
    }

    public myList<T> getNode(int index) {
        return getNode(index, 0);
    }

    public myList<T>[] getNodes(int... indices) {
        myList<T>[] results = new myList[indices.length];
        for (int i = 0; i < indices.length; i++)
            results[i] = getNode(indices[i]);
        return (myList<T>[]) results;
    }

    public myList<T> getNode(T object) {
        return findNode(object);
    }

    public myList<T>[] getNodes(T... objects) {
        return findNodes(objects);
    }

    public myList<T>[] getNodes(Collection<T> objects) {
        return findNodes(objects);
    }

    public myList<T>[] getNodes(myList<T> objects) {
        return findNodes(objects);
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasNext() {
        return next() != null;
    }

    public boolean hasPrevious() {
        return previous() != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean hasRoot() {
        return root != null;
    }

    public T highestValue() {
        if (isEmpty())
            return null;
        else
            return highestValuedNode().data;
    }

    public myList<T> highestValuedNode() {
        if (isEmpty())
            return this;

        myList<T> highest_node = this;
        while (highest_node.hasRight())
            highest_node = highest_node.right;

        return highest_node;
    }

    public int index() {
        int index = hasLeft() ? left.length() : 0;

        // seach up roots to the left until we can't any more and add the lengths of all the roots and their lefts
        myList<T> parsing = this;
        while (parsing.isRight()) {
            parsing = parsing.root;
            index += 1 + (parsing.hasLeft() ? parsing.left.length() : 0);
        }

        return index;
    }

    public int indexOf(T object) {
        return find(object);
    }

    public int[] indicesOf(T... objects) {
        return find(objects);
    }

    public int[] indicesOf(Collection<T> objects) {
        return find(objects);
    }

    public int[] indicesOf(myList<T> objects) {
        return find(objects);
    }

    public int indexOfMatch(Object... match_parameters) {
        return findMatchIndex(match_parameters);
    }

    public int[] indicesOfMatches(Object[]... match_parameters) {
        return findMatchIndices(match_parameters);
    }

    public int[] indicesOfMatches(Collection<Object[]> match_parameters) {
        return findMatchIndices(match_parameters);
    }

    public int[] indicesOfMatches(myList<Object[]> match_parameters) {
        return findMatchIndices(match_parameters);
    }

    public myList<T> intersect(T... list) {
        myList<T> intersect = new myList<T>();
        for (T object : list)
            if (contains(object))
                intersect.add(object);
        return intersect;
    }

    public myList<T> intersect(Collection<T> list) {
        return intersect((T[]) list.toArray());
    }

    public myList<T> intersect(myList<T> list) {
        return intersect(list.toArray());
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return isLeaf() || hasLeft() && hasRight() && left.isFull() && right.isFull();
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean isLeft() {
        return hasRoot() && root.hasLeft() && this == root.left;
    }

    public boolean isRight() {
        return hasRoot() && root.hasRight() && this == root.right;
    }

    public int lastIndexOf(T object) {
        return lastIndexOf(object, hasLeft() ? left.length() : 0);
    }

    public int[] lastIndicesOf(T... objects) {
        int[] results = new int[objects.length];
        for (int i = 0; i < objects.length; i++)
            results[i] = lastIndexOf(objects[i]);
        return results;
    }

    public int[] lastIndicesOf(Collection<T> objects) {
        return lastIndicesOf((T[]) objects.toArray());
    }

    public int[] lastIndicesOf(myList<T> objects) {
        return lastIndicesOf(objects.toArray());
    }

    public int lastIndexOfMatch(Object... match_parameters) {
        return lastIndexOfMatch(match_parameters, hasLeft() ? left.length() : 0);
    }

    public int[] lastIndicesOfMatches(Object[]... match_parameters) {
        int[] results = new int[match_parameters.length];
        for (int i = 0; i < match_parameters.length; i++)
            results[i] = lastIndexOfMatch(match_parameters[i]);
        return results;
    }

    public int[] lastIndicesOfMatches(Collection<Object[]> match_parameters) {
        return lastIndicesOfMatches((String[][]) match_parameters.toArray());
    }

    public int[] lastIndicesOfMatches(myList<Object[]> match_parameters) {
        return lastIndicesOfMatches(match_parameters.toArray());
    }

    public myList<T> left() {
        return left;
    }

    public int length() {
        return size();
    }

    public int levels() {
        if (isEmpty())
            return 0;
        else if (isLeaf())
            return 1;

        // find the number of levels in the left and right lists
        int left_levels = hasLeft() ? left.levels() : 0, right_levels = hasRight() ? right.levels() : 0;

        // return the maximum levels between the left and right lists (+1 for this level)
        if (left_levels >= right_levels)
            return left_levels + 1;
        else
            return right_levels + 1;
    }

    public T lowestValue() {
        if (isEmpty())
            return null;
        else
            return lowestValuedNode().data;
    }

    public myList<T> lowestValuedNode() {
        if (isEmpty())
            return this;

        myList<T> lowest_node = this;
        while (lowest_node.hasLeft())
            lowest_node = lowest_node.left;

        return lowest_node;
    }

    public myList<T> next() {
        if (hasRight())
            return right.lowestValuedNode();
        // if it's the left and has no right, the next is simply the root
        else if (isLeft())
            return root;
        // if a leaf is the right, search up roots until a root is another root's left, then return the root of that root
        else if (isRight()) {
            myList<T> parsing = root;
            // first, go up the roots as long as the root is the right of another root
            while (parsing.isRight())
                parsing = parsing.root;

            /* return the parent of the result, which could be either null if we reached the root (in which case we've reached the end of the list and should return null) or
             * the parent of a left child ancestor of our starting point, which should be the next node */
            return parsing.root;
        }

        return null;
    }

    public myList<T> next(int amount) {
        myList<T> clone = this;
        for (int i = 0; i < amount; i++) {
            clone = clone.next();
            if (clone == null)
                return null;
        }
        return clone;
    }

    public myList<T> previous() {
        // if a leaf has no root, it's a one-item myList, so there is no previous
        if (isEmpty() || isLeaf() && !hasRoot())
            return null;
        else if (hasLeft())
            return left.highestValuedNode();
        // if it's the left and has no right, the next is simply the root
        else if (isRight())
            return root;
        // if a leaf is the right, search up roots until a root is another root's left, then return the root of that root
        else if (isLeft()) {
            myList<T> clone = this;
            // first, go up the roots as long as the root is the right of another root
            while (isLeft())
                clone = clone.root;
            // then, if the root is the root of the whole list (!hasRoot()), we know that there's nothing more to the right of this, so we've reached the end of the list
            if (!clone.hasRoot())
                return null;
            else
                return clone.root;
        }

        return null;
    }

    public myList<T> previous(int amount) {
        myList<T> clone = this;
        for (int i = 0; i < amount; i++) {
            clone = clone.previous();
            if (clone == null)
                return null;
        }
        return clone;
    }

    public void remove(int index) {
        // find the node specified by index
        myList<T> node = getNode(index);
        if (node == null)
            return;

        node.remove();
    }

    public void remove(int... indices) {
        for (int i = 0; i < indices.length; i++)
            remove(indices[i]);
    }

    public int remove(T object) {
        myList<T> node = findNode(object);
        if (node == null)
            return -1;

        int index = node.index();
        node.remove();
        return index;
    }

    public int[] remove(T... objects) {
        int[] results = new int[objects.length];
        for (int i = 0; i < objects.length; i++)
            results[i] = remove(objects[i]);
        return results;
    }

    public int[] remove(Collection<T> objects) {
        return remove((T[]) objects.toArray());
    }

    public int[] removeAll(T object) {
        int index = find(object);
        ArrayList<Integer> indices = new ArrayList<Integer>();

        while (index != -1) {
            remove(index);
            indices.add(index);
            index = find(object);
        }

        int[] results = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++)
            results[i] = indices.get(i);
        return results;
    }

    public int[][] removeAll(T... objects) {
        int[][] results = new int[objects.length][];
        for (int i = 0; i < objects.length; i++)
            results[i] = removeAll(objects[i]);
        return results;
    }

    public int[][] removeAll(Collection<T> objects) {
        return removeAll((T[]) objects.toArray());
    }

    public int[][] removeAll(myList<T> objects) {
        return removeAll(objects.toArray());
    }

    public int[] removeRepeats() {
        if (isEmpty())
            return new int[0];

        myList<T> parsing = lowestValuedNode(), next = parsing.next();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        while (next != null) {
            while (next.compareTo(parsing.data) == 0) {
                int index = next.index();
                indices.add(index);
                remove(index);
                next = parsing.next();
            }
            parsing = next;
            next = parsing.next();
        }

        // convert the ArrayList<Integer> to an int[]
        int[] results = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++)
            results[i] = indices.get(i).intValue();

        return results;
    }

    public int resort(T object) {
        remove(object);
        return add(object);
    }

    public void retain(T... objects) {
        if (isEmpty())
            return;

        myList<T> parsing = lowestValuedNode();
        int index = 0;
        while (parsing != null) {
            while (!ListUtilities.contains(objects, parsing.data)) {
                if (index == 0) {
                    remove(0);
                    parsing = lowestValuedNode();
                } else {
                    parsing = parsing.next();
                    remove(index);
                }
                remove(index);
            }
            index++;
            parsing = parsing.next();
        }
    }

    public void retain(Collection<T> objects) {
        retain((T[]) objects.toArray());
    }

    public void retain(myList<T> objects) {
        // this method is implemented separately from retain(T...) because the myList contains() is more efficient than the array's contains()

        if (isEmpty())
            return;

        myList<T> parsing = lowestValuedNode();
        int index = 0;
        while (parsing != null) {
            while (!objects.contains(parsing.data)) {
                if (index == 0) {
                    remove(0);
                    parsing = lowestValuedNode();
                } else {
                    parsing = parsing.next();
                    remove(index);
                }
                remove(index);
            }
            index++;
            parsing = parsing.next();
        }
    }

    public myList<T> right() {
        return right;
    }

    public int size() {
        return size;
    }

    public int search(T object) {
        return find(object);
    }

    public myList<T>[] split(int... indices) {
        ArrayList<myList<T>> pieces = new ArrayList<myList<T>>();
        myList<T> parsing = lowestValuedNode(), piece = new myList<T>();
        int index = 0;
        while (parsing != null) {
            if (ListUtilities.contains(indices, index)) {
                pieces.add(piece);
                piece = new myList<T>();
            }
            piece.add(parsing.data);

            parsing = parsing.next();
            index++;
        }

        if (piece != null && piece.isEmpty())
            pieces.add(piece);

        return (myList<T>[]) pieces.toArray();
    }

    public myList<T>[] split(T... objects) {
        ArrayList<myList<T>> pieces = new ArrayList<myList<T>>();
        myList<T> parsing = lowestValuedNode(), piece = new myList<T>();
        while (parsing != null) {
            if (ListUtilities.contains(objects, parsing.data)) {
                pieces.add(piece);
                piece = new myList<T>();
            }
            piece.add(parsing.data);

            parsing = parsing.next();
        }

        if (piece != null && piece.isEmpty())
            pieces.add(piece);

        return (myList<T>[]) pieces.toArray();
    }

    public myList<T>[] split(Collection<T> objects) {
        return split((T[]) objects.toArray());
    }

    public myList<T>[] split(myList<T> objects) {
        return split(objects.toArray());
    }

    public myList<T> sublist(int begin_index) {
        return sublist(begin_index, length());
    }

    public myList<T> sublist(int begin_index, int end_index) {
        if (begin_index < 0 || end_index > length())
            throw new IndexOutOfBoundsException();
        return sublist(begin_index, end_index, hasLeft() ? left.length() : 0);
    }

    public T[] toArray() {
        return (T[]) toArrayList().toArray();
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> result = new ArrayList<T>(length());

        // if the list is empty, return an empty ArrayList
        if (isEmpty())
            return result;

        if (hasLeft())
            for (T left_element : left.toArrayList())
                result.add(left_element);
        result.add(data);
        if (hasRight())
            for (T right_element : right.toArrayList())
                result.add(right_element);

        return result;
    }

    public myList<T> union(T... list) {
        // make a new myList
        myList<T> union = clone();
        for (T object : list)
            if (!union.contains(object))
                union.add(object);
        return union;
    }

    public myList<T> union(Collection<T> list) {
        return union((T[]) list.toArray());
    }

    public myList<T> union(myList<T> list) {
        return union(list.toArray());
    }

    // overridable
    public int compare(T object1, T object2) {
        if (data instanceof Comparable<?>)
            try {
                return ((Comparable<T>) object1).compareTo(object2);
            } catch (ClassCastException exception) {
                //
            }

        return object1.toString().compareTo(object2.toString());
    }

    // overrides
    @Override
    public myList<T> clone() {
        return new myList<T>(this);
    }

    public final int compareTo(myList<T> list) {
        if (isEmpty() && list.isEmpty())
            return 0;
        else if (isEmpty())
            return -1;
        else if (list.isEmpty())
            return 1;

        /* keep track of the original sizes of the two lists in order to ensure that if either or both of these lists are sublists, it only compares that sublist, not the rest
         * of the list that the sublist is connected to */
        int this_length = length(), list_length = list.length();

        myList<T> this_parser = lowestValuedNode(), list_parser = list.lowestValuedNode();

        // parse through each list and compare each element until a list ends or there is a difference in the elements
        int comparison = this_parser.compareTo(list_parser.data);
        for (int i = 0; comparison != 0 && i < this_length && i < list_length; i++) {
            this_parser = this_parser.next();
            list_parser = list_parser.next();
            comparison = this_parser.compareTo(list_parser.data);
        }

        // if comparison still = 0, the loop must have terminated because one of the lists ran out of elements, so compare the lengths
        if (comparison == 0)
            return this_length - list_length;
        else
            return comparison;
    }

    @Override
    public final int compareTo(T object) {
        if (data == null)
            if (object == null)
                return 0;
            else
                return -1;
        else if (object == null)
            return 1;
        else
            return compare(data, object);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof myList<?> && ((myList<T>) object).compareTo(this) == 0;
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { data };
    }

    @Override
    public myListIterator<T> iterator() {
        return new myListIterator<T>(this);
    }

    public void remove() {
        remove((myList<T>) null);
    }

    @Override
    public String toString() {
        return toString(", ", "and");
    }

    public String toString(String separator, String final_conjunction) {
        if (isEmpty())
            return "--------";

        String[] strings = new String[size];
        int i = 0, left_size = hasLeft() ? left.size : 0;
        for (myListIterator<T> iterator = new myListIterator<T>(this); iterator.hasNext();) {
            T object = iterator.next();
            /* ensure that the iterator used here never goes to this node's parent to ensure that only the toString() of this sublist is returned */
            if (hasRoot() && i == left_size + 1 /* at i = left_size, the iterator will be at this node; one element later, if this has a parent, it will attempt to go there;
                                                 * instead, force it to go to the right child */)
                if (!hasRight())
                    break;
                else {
                    iterator = right.iterator();
                    object = iterator.next();
                }
            strings[i] = object == null ? "null" : object.toString();
            i++;
        }

        // return the completed ArrayList formatted into a list
        return writeArray(strings, separator, final_conjunction);
    }

}
