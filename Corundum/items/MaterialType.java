package Corundum.items;

public interface MaterialType<T> {
    public byte getData();
    
    public short getID();
    
    public byte getMaxStackSize();
    
    public T[] getSiblings();
    
    public boolean isASiblingOf(T material);
}
