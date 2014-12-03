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

package org.corundummc.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.corundummc.exceptions.CorundumException;

public class ListUtilities {

    // contents
    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return (ArrayList<T>) Arrays.asList(array);
    }

    /** This method combines all of the given <tt>String</tt>s array into a single String.
     * 
     * @param strings
     *            is the list of <tt>String</tt>s that will be combined into a single <tt>String</tt>.
     * @param separator
     *            is the String used to separate the different <tt>String</tt>s, e.g. ", " in the list "apple, orange, lemon, melon"
     * @param indices
     *            is an optional parameter that can be used to select a range of indices in the array <b> <tt>strings</b></tt>. If one index is given, it will be used as the
     *            minimum index (inclusive) for parsing <b><tt>strings</b></tt> for adding pieces to the resultant <tt>String</tt>. If two indices are given, the first index
     *            is used as the minimum index (inclusive) and the second is used as the maximum (non-inclusive).
     * @return the <tt>String</tt> constructed by putting all the <tt>String</tt>s in <b><tt>strings</tt></b> together into one <tt>String</tt>. */
    public static String combine(String[] strings, String separator, int... indices) {
        if (separator == null)
            separator = "";
        int start_index = 0, end_index = strings.length;
        if (indices.length > 0) {
            start_index = indices[0];
            if (indices.length > 1)
                end_index = indices[1];
        }
        String combination = "";
        for (int i = start_index; i < end_index; i++) {
            try {
                combination += strings[i];
            } catch (ArrayIndexOutOfBoundsException exception) {
                // before throwing the exception, write "indices" into an Integer[] so it can be passed as an Object[] into writeArray()
                Integer[] integer_indices = new Integer[indices.length];
                for (int j = 0; j < indices.length; j++)
                    integer_indices[j] = indices[j];  // the (Integer) case on indices[j] is implied

                throw new CorundumException("Someone gave combine() bad indices!", exception, "indices: " + writeArray(integer_indices));
            }
            if (i < end_index - 1)
                combination += separator;
        }
        return combination;
    }

    public static String combine(String[] strings, int... indices) {
        return combine(strings, "", indices);
    }

    /** This method compares two lists of Objects, giving the greatest priority to the first element in the lists. In other words, it compares the first element in the <b>
     * <tt>initial_array</b></tt> to the first element in the <b><tt>compare_array</b></tt> (using {@link Comparable#compareTo(Object)} if available, comparing
     * <tt>toString()</tt> outputs otherwise). If the comparison results in something other than a 0, it returns that value; otherwise, it compares the next element in each
     * array until it gets a non-zero result. If the end of one or both lists is reached, 0 is returned.
     * <hr>
     * If an element in either array is <b>null</b>, the comparison automatically results in a 0; in other words, <b>null</b> can be used as a "wild card".
     * 
     * @param initial_array
     *            is the array that will be compared to <b><tt>compare_array</b></tt>. If <b><tt>initial_array</b></tt> > <b><tt>compare_array</b></tt>, this method will
     *            return a <i>positive</i> integer.
     * @param compare_array
     *            is the array that will be compared to <b><tt>initial_array</b></tt>. If <b><tt>compare_array</b></tt> > <b><tt>initial_array</b></tt>, this method will
     *            return a <i>negative</i> integer.
     * @return the first comparison of elements in the arrays that returns something besides 0 or 0 if all elements are equivalent. */
    @SafeVarargs
    public static <T> int compare(T[] initial_array, T... compare_array) {
        // first, try parsing through the lists and comparing the elements in each
        for (int i = 0; i < initial_array.length && i < compare_array.length; i++) {
            int compare;

            if (initial_array[i] == null || compare_array[i] == null)
                continue;
            else
                try {
                    compare = (int) initial_array[i].getClass().getMethod("compareTo", initial_array[i].getClass()).invoke(compare_array[i]);
                } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
                    compare = initial_array[i].toString().compareTo(compare_array[i].toString());
                }

            if (compare != 0)
                return compare;
        }

        return 0;
    }

    /** This method copies all the <tt>Objects</tt> in all the given <tt>Object[]</tt>s into one large array in which the elements are ordered according to the order of the
     * arrays given as arguments.
     * 
     * @param arrays
     *            is the list of <tt>Object[]</tt>s to be concatenated into one large array.
     * @return one large <tt>Object[]</tt> containing all the elements of the given <b><tt>arrays</b></tt> in the order given. */
    public static <T> T[] concatenate(@SuppressWarnings("unchecked") T[]... arrays) {
        // find the sum of the lengths of all the arrays
        int new_length = 0;
        for (T[] array : arrays)
            new_length += array.length;

        // create the new array
        /* NOTE: I had to make an ArrayList and convert it to an array because you can't make arrays of a generic type */
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new ArrayList<T>(new_length).toArray();

        // copy all the Objects from the arrays into the new array
        int counter = 0;  // counter is necessary because each array is not necessarily the same size
        for (int i = 0; i < arrays.length; i++)
            for (int j = 0; j < arrays[i].length; j++) {
                result[counter] = arrays[i][j];
                counter++;
            }

        return result;
    }

    // TODO: figure out if this method is necessary with the newly parameterized contains(T[], T)
    /** This method determines whether or not a given array contains a given int.
     * 
     * @param ints
     *            is the array of ints that will be searched.
     * @param target
     *            is the int that <b><tt>ints</b></tt> may contain.
     * @return <b>true</b> if <b><tt>object</b></tt> contains <b><tt>target</b></tt>; <b>false</b> otherwise. */
    public static boolean contains(int[] ints, int target) {
        for (int object : ints)
            if (object == target)
                return true;
        return false;
    }

    /** This method determines whether or not a given array contains a given short.
     * 
     * @param shorts
     *            is the array of shorts that will be searched.
     * @param target
     *            is the short that <b><tt>shorts</b></tt> may contain.
     * @return <b>true</b> if <b><tt>object</b></tt> contains <b><tt>target</b></tt>; <b>false</b> otherwise. */
    public static boolean contains(short[] shorts, short target) {
        for (short object : shorts)
            if (object == target)
                return true;
        return false;
    }

    /** This method determines whether or not a given array contains a given Object.
     * 
     * @param objects
     *            is the array of Objects that will be searched.
     * @param target
     *            is the Object that <b><tt>objects</b></tt> may contain.
     * @return <b>true</b> if <b><tt>object</b></tt> contains <b><tt>target</b></tt>; <b>false</b> otherwise. */
    @SafeVarargs
    public static <T> boolean contains(T target, @SuppressWarnings("unchecked") T... objects) {
        for (T object : objects)
            if (object.equals(target))
                return true;
        return false;
    }

    public static boolean containsAND(Object[] objects, Object obj1, Object obj2) {
        return contains(objects, obj1) && contains(objects, obj2);
    }

    public static boolean containsOR(Object[] objects, Object obj1, Object obj2) {
        return contains(objects, obj1) || contains(objects, obj2);
    }

    /** This method separates items in a properly formatted list into individual Strings.
     * 
     * @param list
     *            is the String which will be divided into separate Strings by deconstructing the list framework.
     * @param options
     *            are optional parameters used to change the separator String and the final conjuction String. (By default, these are ", " and "and", respectively.) The first
     *            item is the separator String (the String used to separate the items in the list); the second item is a final conjunction String, a String which may be
     *            attached to the beginning of the last item in the list.
     * @return a String[] of all of the different items in the list given. */
    public static String[] readArray(String list, String... options) {
        String[] objects = null;
        String separator = ", ", final_conjunction = "and";
        if (options.length > 0 && options[0] != null)
            separator = options[0];
        if (options.length > 1 && options[1] != null)
            final_conjunction = options[1];
        // for 3+-item lists
        if (list.contains(separator)) {
            objects = list.split(separator);
            // remove the final conjunction (usually "and") at the beginning of the list object
            if (objects[objects.length - 1].startsWith(final_conjunction + " "))
                objects[objects.length - 1] = objects[objects.length - 1].substring(final_conjunction.length() + 1);
        }
        // for 2-item lists
        else if (list.contains(" " + final_conjunction + " "))
            return list.split(" " + final_conjunction + " ");
        // for 1-item lists
        else
            return new String[] { list };
        return objects;
    }

    public static ArrayList<String> readArrayList(String list, String... options) {
        String[] array = readArray(list, options);
        if (array == null)
            return null;
        ArrayList<String> product = new ArrayList<>();
        product.addAll(Arrays.asList(array));
        return product;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] subArray(T[] objects, int start, int end) {
        try {
            Object[] new_objects = new Object[end - start];
            for (int i = 0; i < new_objects.length; i++)
                new_objects[i] = objects[i + start];
            return (T[]) new_objects;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CorundumException("Someone gave subArray() bad indices!", exception, "array=" + objects, "start=" + start, "end=" + end);
        }
    }

    public static <T> T[] subArray(T[] objects, int start) {
        return subArray(objects, start, objects.length);
    }

    /** This method returns a grammatically correct list that contains all of the items given in a String array.
     * 
     * @param objects
     *            is the String array which will be written into a list.
     * @param separator
     *            is the String that will be used to separate terms in the list; by default, the separator is ", "
     * @param final_conjunction
     *            is the String that will be added between the second to last and last terms in a list of two or more items; by default, the final conjunction is "and"
     * @param prefix
     *            is the String that will be added to the beginning of each item in the list; by default, the prefix is an empty String ("")
     * @param suffix
     *            is the String that will be added to the end of each item in the list; by default, the suffix is an empty String ("")
     * @return a grammatically correct list of the objects in <b><tt>objects</b></tt>. */
    public static String writeArray(Object[] objects, String separator, String final_conjunction, String prefix, String suffix) {
        // establish default for all the arguments
        if (separator == null)
            separator = ", ";
        if (prefix == null)
            prefix = "";
        if (suffix == null)
            suffix = "";
        if (final_conjunction == null)
            final_conjunction = "and";
        else
            final_conjunction = final_conjunction.trim();

        if (objects.length == 0)
            return "";
        else if (objects.length == 1)
            return prefix + objects[0] + suffix;
        else if (objects.length == 2)
            return objects[0] + " " + final_conjunction + " " + objects[1];
        else {
            String list = "";
            for (int i = 0; i < objects.length; i++) {
                list += prefix + objects[i] + suffix;
                if (i < objects.length - 1) {
                    list += separator;
                    if (i == objects.length - 2 && !final_conjunction.equals(""))
                        list += final_conjunction + " ";
                }
            }
            return list;
        }
    }

    public static String writeArray(Object[] objects, String separator, String final_conjunction, String prefix) {
        return writeArray(objects, separator, final_conjunction, prefix, null);
    }

    public static String writeArray(Object[] objects, String separator, String final_conjunction) {
        return writeArray(objects, separator, final_conjunction, null, null);
    }

    public static String writeArray(Object[] objects, String separator) {
        return writeArray(objects, separator, null, null, null);
    }

    public static String writeArray(Object[] objects) {
        return writeArray(objects, null, null, null, null);
    }

    public static String writeArrayList(ArrayList<?> objects, String separator, String final_conjunction, String prefix, String suffix) {
        return writeArray(objects.toArray(), separator, final_conjunction, prefix, suffix);
    }

    public static String writeArrayList(ArrayList<?> objects, String separator, String final_conjunction, String prefix) {
        return writeArrayList(objects, separator, final_conjunction, prefix, null);
    }

    public static String writeArrayList(ArrayList<?> objects, String separator, String final_conjunction) {
        return writeArrayList(objects, separator, final_conjunction, null, null);
    }

    public static String writeArrayList(ArrayList<?> objects, String separator) {
        return writeArrayList(objects, separator, null, null, null);
    }

    public static String writeArrayList(ArrayList<?> objects) {
        return writeArrayList(objects, null, null, null, null);
    }

    public static String writeMap(Map<?, ?> objects) {
        String[] values = new String[objects.keySet().size()];

        int i = 0;
        for (Object key : objects.keySet()) {  // this must be a for each loop with a counter rather than a regular for loop because sets are not indexed
            values[i] = key.toString() + " -> " + objects.get(key).toString();
            i++;
        }

        return writeArray(values, "; ");
    }

}
