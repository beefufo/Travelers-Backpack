package com.tiviacz.travelersbackpack.init;

import com.tiviacz.travelersbackpack.TravelersBackpack;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags
{
    public static final TagKey<Item> BLACKLISTED_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(TravelersBackpack.MODID, "blacklisted_items"));
    public static final TagKey<Item> ACCEPTABLE_TOOLS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(TravelersBackpack.MODID, "acceptable_tools"));
    public static final TagKey<Item> CUSTOM_TRAVELERS_BACKPACK = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(TravelersBackpack.MODID, "custom_travelers_backpack"));
}