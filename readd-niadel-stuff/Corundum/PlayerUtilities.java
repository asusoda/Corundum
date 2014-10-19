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

package Corundum;

import java.util.HashMap;
import java.util.UUID;

import static Corundum.utils.StringUtilities.*;

/** This static singleton class contains a plethora of utilities related to {@link Player}s and {@link Block}s, including methods to convert between total experience and
 * experience levels, case-insensitively autocomplete a player's name, and calculate the block that a given {@link Player} is pointing at.
 * 
 * @author connor */
public class PlayerUtilities {
    /** This {@link HashMap} maps the {@link UUID}s of all the players who have ever joined this server to their last known usernames (i.e. the username they had the last time
     * that they were on this server). If a player logs onto the server with a known username but a different {@link UUID}, then the username associate with that username's
     * old {@link} will be replaced with the <tt>String</tt> form of said old {@link UUID}, preventing the acquisition of the previous username owner's data through
     * "username sniping". */
    public static HashMap<UUID, String> players = new HashMap<UUID, String>();

    /** This enum Object is to be used in specifying different parameters for the {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()} method.
     * 
     * @see {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}; {@link BlockSearch#NON_SOLID}, {@link BlockSearch#LIQUID} ,
     *      {@link BlockSearch#SOLID}, {@link BlockSearch#SWITCH}, and {@link BlockSearch#NON_SWITCH_NON_SOLID} */
    public static enum BlockSearch {
        /** This <tt>BlockSearch</tt> enum value specifies that the block found by <tt>{@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}</tt>
         * must be a non-solid block such as a sign or button; note that all switches (buttons and pressure plates and such as specified by <tt>BlockSearch.<i>SWITCH</i></tt>)
         * are also non-solid blocks.
         * 
         * @see {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}; {@link BlockSearch#LIQUID} , {@link BlockSearch#SOLID},
         *      {@link BlockSearch#SWITCH}, and {@link BlockSearch#NON_SWITCH_NON_SOLID} */
        NON_SOLID,
        /** This <tt>BlockSearch</tt> enum value specifies that the block found by <tt>{@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}</tt>
         * must be a liquid block such as water or lava.
         * 
         * @see {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}; {@link BlockSearch#NON_SOLID} , {@link BlockSearch#SOLID},
         *      {@link BlockSearch#SWITCH}, and {@link BlockSearch#NON_SWITCH_NON_SOLID} */
        LIQUID,
        /** This <tt>BlockSearch</tt> enum value specifies that the block found by <tt>{@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}</tt>
         * must be a solid block such as dirt or a chest.
         * 
         * @see {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}; {@link BlockSearch#NON_SOLID} , {@link BlockSearch#LIQUID},
         *      {@link BlockSearch#SWITCH}, and {@link BlockSearch#NON_SWITCH_NON_SOLID} */
        SOLID,
        /** This <tt>BlockSearch</tt> enum value specifies that the block found by <tt>{@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}</tt>
         * must be a switch such as a button, lever, or pressure plate.
         * 
         * @see {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}; {@link BlockSearch#NON_SOLID} , {@link BlockSearch#LIQUID},
         *      {@link BlockSearch#SOLID}, and {@link BlockSearch#NON_SWITCH_NON_SOLID} */
        SWITCH,
        /** This <tt>BlockSearch</tt> enum value specifies that the block found by <tt>{@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}</tt>
         * must be a non-solid block that si <i>not</i> a switch, e.g. a sign or tall grass block.
         * 
         * @see {@link #getTargetBlock(Player player, BlockSearch... parameters) getTargetBlock()}; {@link BlockSearch#NON_SOLID} , {@link BlockSearch#LIQUID},
         *      {@link BlockSearch#SOLID}, and {@link BlockSearch#SWITCH} */
        NON_SWITCH_NON_SOLID;

        @Override
        public String toString() {
            return replaceAll(name().toLowerCase(), "_", " ");
        }
    }

    /** This method calculates the minimum total number of experience points that a <tt>Player</tt> must have in order to acquire the specified level.
     * 
     * @param level
     *            is the experience level that will be converted to experience points.
     * @return an <b>int</b> representing the number of experience points required at minimum for a <tt>Player</tt> to acquire the given level <b><tt>level</b></tt>.
     * @see {@link #calcLevel(int)} and {@link #calcExactLevel(int)} */
    public static int calcTotalXp(int level) {
        // from the Minecraft Wiki (where x is the level and the result is the amount of total experience required to reach level x):
        // "for x≤16: 17x
        // for 15≤x≤31: 1.5x²-29.5x+360
        // for x≥30: 3.5x²-151.5x+2220"
        if (level < 0)
            return -1;
        else if (level >= 30)
            return (int) (3.5 * Math.pow(level, 2) - 151.5 * level + 2220);
        else if (level <= 16)
            return 17 * level;
        else
            return (int) (1.5 * Math.pow(level, 2) - 29.5 * level + 360);
    }

    /** This method calculates the level that any given <tt>Player</tt> will have if the total number of experience points that they have is the amount specified.
     * 
     * @param xp
     *            is the total number of experience that the hypothetical <tt>Player</tt> has.
     * @return the level that any given <tt>Player</tt> will have if the total number of experience points that they have is the amount specified.
     * @see {@link #calcTotalXp(int)} and {@link #calcExactLevel(int)} */
    public static int calcLevel(int xp) {
        double level = calcExactLevel(xp);
        if (level == -1)
            return -1;
        else
            // round down the exact level value to get the true level
            return (int) calcExactLevel(xp);
    }

    /** This method calculates the level that any given <tt>Player</tt> will have if the total number of experience points that they have is the amount specified. Unlike
     * {@link #calcLevel(int)}, this method will return a double specifying the exact level value that corresponds to the given experience points instead of returning an int
     * representing the shown level value that would be displayed on the <tt>Player</tt>'s U.I.
     * 
     * @param xp
     *            is the total number of experience that the hypothetical <tt>Player</tt> has.
     * @return the precise level value that any given <tt>Player</tt> will have if the total number of experience points that they have is the amount specified. */
    public static double calcExactLevel(int xp) {
        // from the Minecraft Wiki (where x is the level and the result is the amount of total experience required to reach level x):
        // "for x≤16: 17x
        // for 15≤x≤31: 1.5x²-29.5x+360
        // for x≥30: 3.5x²-151.5x+2220"
        // for this method, I have rearranged the equations using the quadratic solution equation to find that (√ = square root) (±s changed to +s to ensure
        // positive results):
        // for level≤16 (xp≤272): xp/17
        // for 15≤level≤31 (255≤xp≤887): (29.5 + √(6*xp - 1,289.75))/3
        // for level≥30 (xp≥825): (151.5 + √(14*xp - 8,127.75))/7
        if (xp < 0)
            return -1;
        else if (xp >= 825)
            return (151.5 + Math.sqrt(14 * xp - 8127.75)) / 7.0;
        else if (xp <= 272)
            return xp / 17.0;
        else
            return (29.5 + Math.sqrt(6 * xp - 1289.75)) / 3.0;

    }

    /** This is a simple auto-complete method that can take the first few letters of a player's name and return the full name of the player. It prioritizes in two ways:
     * <b>1)</b> it gives online players priority over offline players and <b>2)</b> it gives shorter names priority over longer usernames because if a player tries to
     * designate a player and this plugin returns a different name than the user meant that starts with the same letters, the user can add more letters to get the longer
     * username instead. If these priorities were reversed, then there would be no way to specify a user whose username is the first part of another username, e.g. "Jeb" and
     * "Jebs_bro". This matching is <i>not</i> case-sensitive.
     * 
     * @param name
     *            is the String that represents the first few letters of a username that needs to be auto-completed.
     * @return the completed username that begins with <b><tt>name</b></tt> (<i>not</i> case-sensitive) */
    public static String getFullName(String name) {
        for (String player_name : players.values())
            if (player_name.toLowerCase().startsWith(name.toLowerCase()))
                return player_name;

        return null;
    }

    public static String getName(UUID player) {
        return players.get(player);
    }
}