package com.mod.immortal.common.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class ImmortalWorldSavedData extends WorldSavedData
{
	private List<Vec3i> positions = new ArrayList<Vec3i>();
    private List<String> names = new ArrayList<String>();
    private static ImmortalWorldSavedData instance = null;
    
    public ImmortalWorldSavedData(String name)
    {
        super(name);
    }

    public int size()
    {
        return names.size();
    }

    public Vec3i getPosition(int index)
    {
        return positions.get(index);
    }

    public String getName(int index)
    {
        return names.get(index);
    }

    public void add(Vec3i position, String name)
    {
    	if (!names.contains(name)) {
	        positions.add(position);
	        names.add(name);
	        this.markDirty();
    	}
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        positions.clear();
        names.clear();
        NBTTagList list = (NBTTagList) nbt.getTag("positions");
        if (list == null)
        {
            list = new NBTTagList();
        }
        for (int i = list.tagCount() - 1; i >= 0; --i)
        {
            NBTTagCompound compound = (NBTTagCompound) list.get(i);
            positions.add(new Vec3i(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z")));
            names.add(compound.getString("name"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        NBTTagList list = new NBTTagList();
        for (int i = names.size() - 1; i >= 0; --i)
        {
        	Vec3i position = positions.get(i);
            String name = names.get(i);
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("x", position.getX());
            compound.setInteger("y", position.getY());
            compound.setInteger("z", position.getZ());
            compound.setString("name", name);
            list.appendTag(compound);
        }
        nbt.setTag("positions", list);
        return nbt;
    }
    
    
    public static ImmortalWorldSavedData get(World world) {
    	if (ImmortalWorldSavedData.instance == null) {
	    	WorldSavedData data = world.getMapStorage().getOrLoadData(ImmortalWorldSavedData.class, "ImmortalHolyLands");
	    	if (data == null) {
	    		data = new ImmortalWorldSavedData("ImmortalHolyLands");
	    		world.getPerWorldStorage().setData("ImmortalHolyLands", data);
	    	}
	    	ImmortalWorldSavedData.instance = (ImmortalWorldSavedData)data;
    	}
		return ImmortalWorldSavedData.instance;
    }
    
    
}