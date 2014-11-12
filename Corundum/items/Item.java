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

package Corundum.items;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import Corundum.Corundum;
import Corundum.exceptions.CIE;
import Corundum.utils.interfaces.HoldableType;
import Corundum.utils.interfaces.IDedType;
import Corundum.utils.interfaces.Matchable;
import Corundum.utils.myList.myList;

public class Item {
    private final ItemType type;
    private myList<Enchantment> enchantments;

    // TODO private InventorySlot location;

    public Item(ItemType type, Enchantment... enchantments) {
        this.type = type;
        this.enchantments = new myList<>(enchantments);
    }

    // TODO: add methods for adding, removing, and modifying enchantments

    public myList<Enchantment> getEnchantments() {
        return enchantments;
    }

    public ItemType getType() {
        return type;
    }

    public static Item[] fromMCItems(ItemStack... item_stacks) {
        // TODO
        return null;
    }
}
