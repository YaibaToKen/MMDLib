package com.mcmoddev.lib.item;

import java.util.List;

import com.mcmoddev.lib.data.Names;
//import com.mcmoddev.basemetals.items.MMDToolEffects;
import com.mcmoddev.lib.material.IMMDObject;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 *
 * @author Jasmine Iwanek
 *
 */
public class ItemMMDCrossbow extends ItemCrossbow implements IMMDObject {

	private final MMDMaterial material;

	/**
	 *
	 * @param material
	 *            The material to make the crossbow from
	 */
	public ItemMMDCrossbow(final MMDMaterial material) {
		this.material = material;
		this.maxStackSize = 1;
		this.setMaxDamage(this.material.getToolDurability());
	}

	@Override
	public boolean getIsRepairable(final ItemStack intputItem, final ItemStack repairMaterial) {
		return MMDItemHelper.isToolRepairable(repairMaterial, this.material.getCapitalizedName());
	}

	@Override
	public void onUpdate(final ItemStack item, final World world, final Entity player,
			final int inventoryIndex, final boolean isHeld) {
		MMDItemHelper.doRegeneration(item, world, isHeld, this.material.regenerates());
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn,
			final List<String> tooltip, final ITooltipFlag flagIn) {
		List<String> tt = this.getMMDMaterial().getTooltipFor(Names.CROSSBOW);
		if(!tt.isEmpty())
			tooltip.addAll(tt);
	}

	@Override
	public MMDMaterial getMMDMaterial() {
		return this.material;
	}
}
