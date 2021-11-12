package com.blamejared.jeitweaker.zen.component;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.jeitweaker.api.BuiltinIngredientTypes;
import org.openzen.zencode.java.ZenCodeType;

//@Document("mods/JEI/Component/IFluidStackExpansions")
@ZenCodeType.Expansion("crafttweaker.api.fluid.IFluidStack")
@ZenRegister
public final class IFluidStackExpansions {
    @ZenCodeType.Caster(implicit = true)
    public static RawJeiIngredient asJeiIngredient(final IFluidStack stack) {

        return new JeiIngredient<>(BuiltinIngredientTypes.FLUID.get(), stack);
    }

    @ZenCodeType.Caster(implicit = true)
    public static JeiDrawable asJeiDrawable(final IFluidStack stack) {

        return JeiIngredientExpansions.asJeiDrawable(asJeiIngredient(stack));
    }
}
