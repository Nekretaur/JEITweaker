package com.blamejared.jeitweaker;

import com.blamejared.crafttweaker.api.item.*;
import com.blamejared.crafttweaker.impl.recipes.*;
import com.blamejared.jeitweaker.display_tests.*;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.*;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class JEIAddonPlugin implements IModPlugin {
    
    public static final IIngredientType<IIngredient> I_INGREDIENT_TYPE = () -> IIngredient.class;
    public static IRecipeManager recipeManager = null;
    
    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
        registration.register(I_INGREDIENT_TYPE, new ArrayList<>(), new CTIngredientHelper(), CTIngredientRenderer.INSTANCE);
    }
    
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    }
    
    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
    }
    
    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
    }
    
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    
    }
    
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IIngredientManager ingredientManager = registration.getIngredientManager();
        IIngredientType<ItemStack> type = ingredientManager.getIngredientType(ItemStack.class);
        JEIManager.ITEM_DESCRIPTIONS.forEach((key, value) -> registration.addIngredientInfo(key.getInternal(), type, value));
        JEIManager.ITEM_DESCRIPTIONS.clear();
    }
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {
        IIngredientManager ingredientManager = iJeiRuntime.getIngredientManager();
        IIngredientType<ItemStack> type = ingredientManager.getIngredientType(ItemStack.class);
        
        final List<ItemStack> collect = JEIManager.HIDDEN_ITEMS.stream()
                .map(IItemStack::getInternal)
                .collect(Collectors.toList());
        if(!collect.isEmpty()) {
            ingredientManager.removeIngredientsAtRuntime(type, collect);
        }
        recipeManager = iJeiRuntime.getRecipeManager();
        JEIManager.HIDDEN_RECIPE_CATEGORIES.stream().map(ResourceLocation::new).forEach(recipeManager::hideRecipeCategory);
        JEIManager.HIDDEN_ITEMS.clear();
        JEIManager.HIDDEN_RECIPE_CATEGORIES.clear();
    }
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("jeitweaker:main");
    }
    
    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getCraftingCategory().addCategoryExtension(CTRecipeShaped.class, CTCraftingRecipeExtension::new);
    }
    
    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
        registration.addRecipeManagerPlugin(new CTRecipeManagerPlugin());
    }
}