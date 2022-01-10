package com.blamejared.jeitweaker.actions;

import com.blamejared.crafttweaker.api.ScriptLoadingOptions;
import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.jeitweaker.implementation.state.StateManager;
import net.minecraft.resources.ResourceLocation;

public final class ActionHideCategory implements IUndoableAction {

    private final ResourceLocation category;

    public ActionHideCategory(final ResourceLocation category) {

        this.category = category;
    }

    @Override
    public void apply() {
    
        StateManager.INSTANCE.actionsState().hideRecipeCategory(this.category);
    }

    @Override
    public void undo() {
    
        StateManager.INSTANCE.actionsState().showRecipeCategory(this.category);
    }

    @Override
    public String describeUndo() {

        return "Undoing JEI Category hiding: " + this.category;
    }

    @Override
    public String describe() {

        return "Hiding Category '" + this.category + "' from JEI";
    }
    
    @Override
    public boolean shouldApplyOn(ScriptLoadingOptions.ScriptLoadSource source) {
        
        return ScriptLoadingOptions.CLIENT_RECIPES_UPDATED_SCRIPT_SOURCE.equals(source);
    }

}
