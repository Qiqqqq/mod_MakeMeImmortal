package com.mod.immortal.common.lib;

public enum FiveEleTypes {

    JIN(0, 1),
    MU(1, 2),
    SHUI(2, 3),
    HUO(3, 4),
    TU(0, 5);

    private final int var1;
    private final int var2;

    private FiveEleTypes(int var1, int var2)
    {
        this.var1 = var1;
        this.var2 = var2;
    }

	public int getVar1() {
		return var1;
	}

	public int getVar2() {
		return var2;
	}

}
