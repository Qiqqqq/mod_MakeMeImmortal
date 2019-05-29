package com.mod.immortal.common.world.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.immortal.common.block.BlockLoader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

@SuppressWarnings("incomplete-switch")
public class StructureHolyLandPieces
{
    private static final StructureHolyLandPieces.Stones STRONGHOLD_STONES = new StructureHolyLandPieces.Stones();
    private static Class <? extends StructureHolyLandPieces.HolyLand > strongComponentType;
    
    private static final Map <Integer , Class <? extends StructureHolyLandPieces.HolyLand >>  templateLayout = Maps.newHashMap();
    static
    {
    	templateLayout.put(1, StructureHolyLandPieces.Library.class);
    	templateLayout.put(3, StructureHolyLandPieces.Straight.class);
    	templateLayout.put(4, StructureHolyLandPieces.PortalRoom.class);
    }
    
    public static void registerHolyLandPieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureHolyLandPieces.Library.class, "HLLi");
        MapGenStructureIO.registerStructureComponent(StructureHolyLandPieces.PortalRoom.class, "HLPR");
        MapGenStructureIO.registerStructureComponent(StructureHolyLandPieces.Stairs.class, "HLSD");
        MapGenStructureIO.registerStructureComponent(StructureHolyLandPieces.Straight.class, "HLS");
    }
    
    private static StructureHolyLandPieces.HolyLand findAndCreatePieceFactory(Class <? extends StructureHolyLandPieces.HolyLand > clazz, List<StructureComponent> componentList, Random rand, int minX, int minY, int minZ, @Nullable EnumFacing facing, int type)
    {
        StructureHolyLandPieces.HolyLand structureholyLandpieces$holyLand = null;

        if (clazz == StructureHolyLandPieces.Straight.class)
        {
            structureholyLandpieces$holyLand = StructureHolyLandPieces.Straight.createPiece(componentList, rand, minX, minY, minZ, facing, type);
        }
        else if (clazz == StructureHolyLandPieces.Stairs.class)
        {
            structureholyLandpieces$holyLand = StructureHolyLandPieces.Stairs.createPiece(componentList, rand, minX, minY, minZ, facing, type);
        }
        else if (clazz == StructureHolyLandPieces.Library.class)
        {
            structureholyLandpieces$holyLand = StructureHolyLandPieces.Library.createPiece(componentList, rand, minX, minY, minZ, facing, type);
        }
        else if (clazz == StructureHolyLandPieces.PortalRoom.class)
        {
            structureholyLandpieces$holyLand = StructureHolyLandPieces.PortalRoom.createPiece(componentList, rand, minX, minY, minZ, facing, type);
        }

        return structureholyLandpieces$holyLand;
    }
    
    private static StructureComponent generateAndAddPiece(List<StructureComponent> componentList, Random rand, int minX, int minY, int minZ, @Nullable EnumFacing facing, int type)
    {
    	if (strongComponentType != null)
        {
    		StructureHolyLandPieces.HolyLand holyland = findAndCreatePieceFactory(strongComponentType, componentList, rand, minX, minY, minZ, facing, type);
    		strongComponentType = null;

            if (holyland != null)
            {
                return holyland;
            }
        }
		return null;
    }


    public static class Library extends StructureHolyLandPieces.HolyLand
    {
        private boolean isLargeRoom;

        public Library()
        {
        }

        public Library(int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = boundingBox;
            this.isLargeRoom = boundingBox.getYSize() > 6;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Tall", this.isLargeRoom);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.isLargeRoom = tagCompound.getBoolean("Tall");
        }

        public static StructureHolyLandPieces.Library createPiece(List<StructureComponent> componentList, Random rand, int minX, int minY, int minZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, -4, -1, 0, 14, 11, 15, facing);

            if (StructureComponent.findIntersecting(componentList, structureboundingbox) != null)
            {
                structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, -4, -1, 0, 14, 6, 15, facing);

                if (StructureComponent.findIntersecting(componentList, structureboundingbox) != null)
                {
                    return null;
                }
            }

            return new StructureHolyLandPieces.Library(type, rand, structureboundingbox, facing);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
            {
                return false;
            }
            else
            {
                int i = 11;

                if (!this.isLargeRoom)
                {
                    i = 6;
                }

                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 13, i - 1, 14, true, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);

                for (int l = 1; l <= 13; ++l)
                {
                    if ((l - 1) % 4 == 0)
                    {
                        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                        this.setBlockState(worldIn, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST), 2, 3, l, structureBoundingBoxIn);
                        this.setBlockState(worldIn, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), 11, 3, l, structureBoundingBoxIn);

                        if (this.isLargeRoom)
                        {
                            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                        }
                    }
                    else
                    {
                        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
                        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);

                        if (this.isLargeRoom)
                        {
                            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
                            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
                        }
                    }
                }

                for (int k1 = 3; k1 < 12; k1 += 2)
                {
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, k1, 4, 3, k1, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, k1, 7, 3, k1, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, k1, 10, 3, k1, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
                }

                if (this.isLargeRoom)
                {
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 3, 5, 13, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 5, 1, 12, 5, 13, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 1, 9, 5, 2, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 12, 9, 5, 13, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
                    this.setBlockState(worldIn, Blocks.PLANKS.getDefaultState(), 9, 5, 11, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.PLANKS.getDefaultState(), 8, 5, 11, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.PLANKS.getDefaultState(), 9, 5, 10, structureBoundingBoxIn);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 6, 2, 3, 6, 12, Blocks.OAK_FENCE.getDefaultState(), Blocks.OAK_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 6, 2, 10, 6, 10, Blocks.OAK_FENCE.getDefaultState(), Blocks.OAK_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 2, 9, 6, 2, Blocks.OAK_FENCE.getDefaultState(), Blocks.OAK_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 12, 8, 6, 12, Blocks.OAK_FENCE.getDefaultState(), Blocks.OAK_FENCE.getDefaultState(), false);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 9, 6, 11, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 8, 6, 11, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 9, 6, 10, structureBoundingBoxIn);
                    IBlockState iblockstate1 = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH);
                    this.setBlockState(worldIn, iblockstate1, 10, 1, 13, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate1, 10, 2, 13, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate1, 10, 3, 13, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate1, 10, 4, 13, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate1, 10, 5, 13, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate1, 10, 6, 13, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate1, 10, 7, 13, structureBoundingBoxIn);
                    
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 6, 9, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 9, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 6, 8, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 8, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 6, 7, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 7, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 5, 7, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 8, 7, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 6, 7, 6, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 6, 7, 8, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 7, 6, structureBoundingBoxIn);
                    this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 7, 8, structureBoundingBoxIn);
                    IBlockState iblockstate = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.UP);
                    this.setBlockState(worldIn, iblockstate, 5, 8, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate, 8, 8, 7, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate, 6, 8, 6, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate, 6, 8, 8, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate, 7, 8, 6, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate, 7, 8, 8, structureBoundingBoxIn);
                }
                return true;
            }
        }
    }
    
    public static class PortalRoom extends StructureHolyLandPieces.HolyLand
    {
    	
        public PortalRoom()
        {
        }
        
        public PortalRoom(int type, Random rand, int xMin, int zMin)
        {
            super(type);
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));
            this.boundingBox = new StructureBoundingBox(xMin, 64, zMin, xMin + 5 - 1, 74, zMin + 5 - 1);
        }
        
        public PortalRoom(int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = boundingBox;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager)
        {
            super.readStructureFromNBT(tagCompound, templateManager);
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal(listIn, rand, 1, 1);
        }

        public static StructureHolyLandPieces.PortalRoom createPiece(List<StructureComponent> componentList, Random rand, int minX, int minY, int minZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, -4, -1, 0, 11, 8, 16, facing);
            return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new StructureHolyLandPieces.PortalRoom(type, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 10, 7, 15, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            int i = 6;
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, i, 1, 1, i, 14, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, i, 1, 9, i, 14, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, i, 1, 8, i, 2, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, i, 14, 8, i, 14, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 2, 1, 4, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 8, 1, 1, 9, 1, 4, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 1, 1, 3, Blocks.FLOWING_LAVA.getDefaultState(), Blocks.FLOWING_LAVA.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 1, 9, 1, 3, Blocks.FLOWING_LAVA.getDefaultState(), Blocks.FLOWING_LAVA.getDefaultState(), false);
            this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 3, 1, 8, 7, 1, 12, false, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 6, 1, 11, Blocks.FLOWING_LAVA.getDefaultState(), Blocks.FLOWING_LAVA.getDefaultState(), false);

            for (int j = 3; j < 14; j += 2)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, j, 0, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 3, j, 10, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            }

            for (int i1 = 2; i1 < 9; i1 += 2)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, 3, 15, i1, 4, 15, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            }

            return true;
        }
    }

    public static class Stairs extends StructureHolyLandPieces.HolyLand
    {
        public Stairs()
        {
        }

        public Stairs(int type, Random rand, int xMin, int zMin)
        {
            super(type);
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(xMin, 64, zMin, xMin + 5 - 1, 74, zMin + 5 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(xMin, 64, zMin, xMin + 5 - 1, 74, zMin + 5 - 1);
            }
        }

        public Stairs(int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = boundingBox;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            StructureHolyLandPieces.strongComponentType = StructureHolyLandPieces.PortalRoom.class;
        }

        public static StructureHolyLandPieces.Stairs createPiece(List<StructureComponent> componentList, Random rand, int minX, int minY, int minZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, -1, -7, 0, 5, 11, 5, facing);
            return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new StructureHolyLandPieces.Stairs(type, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 10, 4, true, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 6, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 1, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 6, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 1, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 1, 4, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 5, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 4, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 3, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 3, 4, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 3, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 3, 2, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 3, 3, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 2, 2, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 2, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 1, 3, structureBoundingBoxIn);
                return true;
            }
        }
    }

    public static class Straight extends StructureHolyLandPieces.HolyLand
    {
        private boolean expandsX;
        private boolean expandsZ;

        public Straight()
        {
        }

        public Straight(int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = boundingBox;
            this.expandsX = rand.nextInt(2) == 0;
            this.expandsZ = rand.nextInt(2) == 0;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Left", this.expandsX);
            tagCompound.setBoolean("Right", this.expandsZ);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager)
        {
            super.readStructureFromNBT(tagCompound, templateManager);
            this.expandsX = tagCompound.getBoolean("Left");
            this.expandsZ = tagCompound.getBoolean("Right");
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal(listIn, rand, 1, 1);
        }

        public static StructureHolyLandPieces.Straight createPiece(List<StructureComponent> componentList, Random rand, int minX, int minY, int minZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, -1, -1, 0, 5, 5, 7, facing);
            return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new StructureHolyLandPieces.Straight(type, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, true, randomIn, StructureHolyLandPieces.STRONGHOLD_STONES);
                IBlockState iblockstate = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST);
                IBlockState iblockstate1 = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST);
                this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 1, 2, 1, iblockstate);
                this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 3, 2, 1, iblockstate1);
                this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 1, 2, 5, iblockstate);
                this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 3, 2, 5, iblockstate1);

                if (this.expandsX)
                {
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 2, 0, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }

                if (this.expandsZ)
                {
                    this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 2, 4, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }

                return true;
            }
        }
    }
    
    static class Stones extends StructureComponent.BlockSelector
    {
        private Stones()
        {
        }

        public void selectBlocks(Random rand, int x, int y, int z, boolean wall)
        {
            if (wall)
            {
                float f = rand.nextFloat();

                if (f < 0.8F)
                {
                    this.blockstate = BlockLoader.MATERIAL_STONE.getDefaultState();
                }
                else
                {
                    this.blockstate = Blocks.STONEBRICK.getDefaultState();
                }
            }
            else
            {
                this.blockstate = Blocks.AIR.getDefaultState();
            }
        }
    }

    static class PieceWeight
    {
        public Class <? extends StructureHolyLandPieces.HolyLand > pieceClass;
        /**
         * This basically keeps track of the 'epicness' of a structure. Epic structure components have a higher
         * 'weight', and Structures may only grow up to a certain 'weight' before generation is stopped
         */
        public final int pieceWeight;
        public int instancesSpawned;
        /** How many Structure Pieces of this type may spawn in a structure */
        public int instancesLimit;

        public PieceWeight(Class <? extends StructureHolyLandPieces.HolyLand > p_i2076_1_, int p_i2076_2_, int p_i2076_3_)
        {
            this.pieceClass = p_i2076_1_;
            this.pieceWeight = p_i2076_2_;
            this.instancesLimit = p_i2076_3_;
        }

        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }

        public boolean canSpawnMoreStructures()
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
    }

    public abstract static class HolyLand extends StructureComponent
    {

        public HolyLand()
        {
        }

        protected HolyLand(int type)
        {
            super(type);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
        }

        /**
         * Gets the next component in any cardinal direction
         */
        @Nullable
        protected StructureComponent getNextComponentNormal(List<StructureComponent> componentList, Random rand, int offsetXZ, int offsetY)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                        return StructureHolyLandPieces.generateAndAddPiece(componentList, rand, this.boundingBox.minX + offsetXZ, this.boundingBox.minY + offsetY, this.boundingBox.minZ - 1, enumfacing, this.getComponentType());
                    case SOUTH:
                        return StructureHolyLandPieces.generateAndAddPiece(componentList, rand, this.boundingBox.minX + offsetXZ, this.boundingBox.minY + offsetY, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType());
                    case WEST:
                        return StructureHolyLandPieces.generateAndAddPiece(componentList, rand, this.boundingBox.minX - 1, this.boundingBox.minY + offsetY, this.boundingBox.minZ + offsetXZ, enumfacing, this.getComponentType());
                    case EAST:
                        return StructureHolyLandPieces.generateAndAddPiece(componentList, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + offsetY, this.boundingBox.minZ + offsetXZ, enumfacing, this.getComponentType());
                }
            }

            return null;
        }

    }
}