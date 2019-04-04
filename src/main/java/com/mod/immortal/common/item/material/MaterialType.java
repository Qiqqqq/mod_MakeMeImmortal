package com.mod.immortal.common.item.material;

public enum  MaterialType {
    DREAM_GLASS("dreamGlass","dream_glass");
    public final String unlocalizedName;
    public final String registryName;
    MaterialType(String unlocalizedName, String registryName)
    {
        this.unlocalizedName = unlocalizedName;
        this.registryName = registryName;
    }
}
