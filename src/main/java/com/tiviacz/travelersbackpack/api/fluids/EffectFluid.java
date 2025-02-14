package com.tiviacz.travelersbackpack.api.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class EffectFluid
{
    public Fluid fluid;
    public int effectID;
    public int amountRequired;

    public EffectFluid(FluidStack fluidStack, int amountRequired)
    {
        this(fluidStack.getFluid(), amountRequired);
    }

    public EffectFluid(Fluid fluid, int amountRequired)
    {
        this.fluid = fluid;
        this.effectID = 0;
        this.amountRequired = amountRequired;

        if(fluid != null)
        {
            com.tiviacz.travelersbackpack.fluids.EffectFluidRegistry.registerFluidEffect(this);
        }
    }

    public EffectFluid(String modid, String fluidName, int amountRequired)
    {
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(modid, fluidName));
        this.fluid = fluid;
        this.effectID = 0;
        this.amountRequired = amountRequired;

        if(fluid != null)
        {
            com.tiviacz.travelersbackpack.fluids.EffectFluidRegistry.registerFluidEffect(this);
        }
    }

    public void setEffectID(int id)
    {
        effectID = id;
    }

    public int getEffectID()
    {
        return effectID;
    }

    /**
     * This method determines what will happen to the player (or world!) when drinking the
     * corresponding fluid. For example set potion effects, set player on fire,
     * heal, fill hunger, etc. You can use the world parameter to make
     * conditions based on where the player is.
     *
     * @param world  The World.
     * @param entity The entity that will be affected.
     */
    public abstract void affectDrinker(FluidStack stack, Level world, Entity entity);

    /**
     * This method runs before hose is used.
     *
     * @param world  The World.
     * @param entity The entity that will be affected.
     */
    public abstract boolean canExecuteEffect(FluidStack stack, Level world, Entity entity);
}