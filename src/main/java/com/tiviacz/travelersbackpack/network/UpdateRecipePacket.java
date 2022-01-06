package com.tiviacz.travelersbackpack.network;

import com.tiviacz.travelersbackpack.client.gui.TravelersBackpackScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateRecipePacket
{
    public static final ResourceLocation NULL = new ResourceLocation("null", "null");

    private final ResourceLocation recipeId;
    private final ItemStack output;

    public UpdateRecipePacket(IRecipe recipe, ItemStack output)
    {
        this.recipeId = recipe == null ? NULL : recipe.getId();
        this.output = output;
    }

    public UpdateRecipePacket(ResourceLocation recipeId, ItemStack output)
    {
        this.recipeId = recipeId;
        this.output = output;
    }

    public static UpdateRecipePacket decode(final PacketBuffer buffer)
    {
        ResourceLocation recipeId = new ResourceLocation(buffer.readString());

        return new UpdateRecipePacket(recipeId, recipeId.equals(NULL) ? ItemStack.EMPTY : buffer.readItemStack());
    }

    public static void encode(final UpdateRecipePacket message, final PacketBuffer buffer)
    {
        buffer.writeString(message.recipeId.toString());
        if(!message.recipeId.equals(NULL))
        {
            buffer.writeItemStack(message.output);
        }
    }

    public static void handle(final UpdateRecipePacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

            IRecipe<?> recipe = Minecraft.getInstance().world.getRecipeManager().getRecipe(message.recipeId).orElse(null);
            if(Minecraft.getInstance().currentScreen instanceof TravelersBackpackScreen)
            {
                ((TravelersBackpackScreen)Minecraft.getInstance().currentScreen).getContainer().craftResult.setRecipeUsed(recipe); // = (ICraftingRecipe)recipe;
                ((TravelersBackpackScreen)Minecraft.getInstance().currentScreen).getContainer().craftResult.setInventorySlotContents(0, message.output);
            }
        }));

        ctx.get().setPacketHandled(true);
    }
}
