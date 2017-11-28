package com.reptiles.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMegaMeat extends ItemFood {
    public ItemMegaMeat(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(Reptiles.MODID + ":" + name);
        setCreativeTab(CreativeTabs.FOOD);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
}
