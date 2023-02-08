package com.blamejared.jeitweaker.common.api.action;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.jeitweaker.common.api.JeiTweakerApi;
import com.blamejared.jeitweaker.common.api.command.JeiCommand;

public abstract class JeiTweakerAction implements IRuntimeAction {
    protected final <T> void enqueueCommand(final JeiCommand<T> command) {
        JeiTweakerApi.get().enqueueCommand(command);
    }
    
    @Override
    public boolean shouldApplyOn(final IScriptLoadSource source) {
        return JeiTweakerApi.get().shouldApplyAction();
    }
    
}
