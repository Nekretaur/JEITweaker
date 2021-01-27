package com.blamejared.jeitweaker;

import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.*;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.*;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;
import java.util.stream.Collectors;


@JeiPlugin
public class JEIAddonPlugin implements IModPlugin {
    
    public static final List<ResourceLocation> JEI_CATEGORIES = new ArrayList<>();
    
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IIngredientManager ingredientManager = registration.getIngredientManager();
        IIngredientType<ItemStack> itemType = ingredientManager.getIngredientType(ItemStack.class);
        IIngredientType<FluidStack> fluidType = ingredientManager.getIngredientType(FluidStack.class);
        JEIManager.ITEM_DESCRIPTIONS.forEach((key, value) -> registration.addIngredientInfo(key.getInternal(), itemType, value));
        JEIManager.FLUID_DESCRIPTIONS.forEach((key, value) -> registration.addIngredientInfo(key.getInternal(), fluidType, value));
        JEIManager.ITEM_DESCRIPTIONS.clear();
        JEIManager.FLUID_DESCRIPTIONS.clear();
    }
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {
        
        IIngredientManager ingredientManager = iJeiRuntime.getIngredientManager();
        IIngredientType<ItemStack> itemType = ingredientManager.getIngredientType(ItemStack.class);
        IIngredientType<FluidStack> fluidType = ingredientManager.getIngredientType(FluidStack.class);
    
        if(!JEIManager.HIDDEN_ITEMS.isEmpty()) {
            ingredientManager.removeIngredientsAtRuntime(itemType, JEIManager.HIDDEN_ITEMS.stream().map(IItemStack::getInternal).collect(Collectors.toList()));
        }
        if(!JEIManager.HIDDEN_FLUIDS.isEmpty()) {
            ingredientManager.removeIngredientsAtRuntime(fluidType, JEIManager.HIDDEN_FLUIDS.stream().map(IFluidStack::getInternal).collect(Collectors.toList()));
        }
        JEIManager.HIDDEN_RECIPE_CATEGORIES.stream().map(ResourceLocation::new).forEach(iJeiRuntime.getRecipeManager()::hideRecipeCategory);
        JEIManager.HIDDEN_ITEMS.clear();
        JEIManager.HIDDEN_FLUIDS.clear();
        JEIManager.HIDDEN_RECIPE_CATEGORIES.clear();
        JEI_CATEGORIES.clear();
        JEI_CATEGORIES.addAll(iJeiRuntime.getRecipeManager().getRecipeCategories().stream().map(IRecipeCategory::getUid).collect(Collectors.toList()));
    }
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("jeitweaker:main");
    }
}