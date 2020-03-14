package com.blamejared.jeitweaker;

import com.blamejared.crafttweaker.api.item.IItemStack;
import mezz.jei.api.*;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.ingredients.*;
import mezz.jei.api.runtime.*;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;

import java.util.stream.Collectors;


@JeiPlugin
public class JEIAddonPlugin implements IModPlugin {
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {
        IIngredientManager ingredientManager = iJeiRuntime.getIngredientManager();
        IIngredientType<ItemStack> type = ingredientManager.getIngredientType(ItemStack.class);
        ingredientManager.removeIngredientsAtRuntime(type, JEIManager.HIDDEN_ITEMS.stream().map(IItemStack::getInternal).collect(Collectors.toList()));
        JEIManager.HIDDEN_RECIPE_CATEGORIES.stream().map(ResourceLocation::new).forEach(iJeiRuntime.getRecipeManager()::hideRecipeCategory);
        JEIManager.HIDDEN_ITEMS.clear();
        JEIManager.HIDDEN_RECIPE_CATEGORIES.clear();
    }
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("jeitweaker:main");
    }
}