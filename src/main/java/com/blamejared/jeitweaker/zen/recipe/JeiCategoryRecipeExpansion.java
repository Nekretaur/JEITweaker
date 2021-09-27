package com.blamejared.jeitweaker.zen.recipe;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.jeitweaker.actions.ActionAddRecipeToCategory;
import com.blamejared.jeitweaker.zen.category.JeiCategory;
import com.blamejared.jeitweaker.zen.component.HackyJeiIngredientToMakeZenCodeHappy;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

//@Document("mods/JEITweaker/Recipe/JeiCategoryRecipeExpansion")
@ZenCodeType.Expansion("mods.jei.category.JeiCategory")
@ZenRegister
public final class JeiCategoryRecipeExpansion {
    
    @ZenCodeType.Method
    public static void addRecipe(final JeiCategory category, final HackyJeiIngredientToMakeZenCodeHappy[][] outputs, final HackyJeiIngredientToMakeZenCodeHappy[][] inputs) {
        
        addRecipe(category, outputs, inputs, graphics -> {});
    }
    
    @ZenCodeType.Method
    public static void addRecipe(
            final JeiCategory category,
            final HackyJeiIngredientToMakeZenCodeHappy[][] outputs,
            final HackyJeiIngredientToMakeZenCodeHappy[][] inputs,
            final Consumer<RecipeGraphics> graphics
    ) {
        
        final JeiRecipe recipe = new JeiRecipe(category, inputs, outputs, graphics);
        CraftTweakerAPI.apply(new ActionAddRecipeToCategory(category, recipe));
    }
}
