package org.corundummc.utils.interfaces;

public interface ArmorMaterial {
    // TODO: in ArmorType, make an abstract method for getting the protection level of a given piece of armor
    
    /* TODO: public ArmorType[] getOutfit(); returns a 4-item array with the four armor items that can be made from this material */
    
    // TODO: public BootType getBoots();
    
    // TODO: public LeggingsType getLeggings();
    
    // TODO: public ChestplateType getChestplate();
    
    // TODO: public HelmetType getHelmet();

    /** This method returns a <b>byte</b> representing the strength of armor made of this {@link ArmorMaterial} relative to other {@link ArmorMaterial}s; a greater strength
     * level means that armor made of this {@link ArmorMaterial} will protect the wearer more and last longer than armor made of an {@link ArmorMaterial} with a lower strength
     * value.
     * 
     * @return the strength leve of this {@link ArmorMaterial} as a <b>byte</b>. */
    public byte getStrength();
}
