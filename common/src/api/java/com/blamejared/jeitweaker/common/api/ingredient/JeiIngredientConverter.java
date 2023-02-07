package com.blamejared.jeitweaker.common.api.ingredient;

import net.minecraft.resources.ResourceLocation;

public interface JeiIngredientConverter<J, Z> {
    JeiIngredientCreator.Creator<J, Z> toFullIngredientFromJei(final JeiIngredientCreator.FromJei creator, final J jeiType);
    JeiIngredientCreator.Creator<J, Z> toFullIngredientFromZen(final JeiIngredientCreator.FromZen creator, final Z zenType);
    JeiIngredientCreator.Creator<J, Z> toFullIngredientFromBoth(final JeiIngredientCreator.FromBoth creator, final J jeiType, final Z zenType);
    
    J toJeiFromZen(final Z zenType);
    Z toZenFromJei(final J jeiType);
    String toCommandStringFromZen(final Z zenType);
    ResourceLocation toRegistryNameFromJei(final J jeiType);
    
    default String toCommandStringFromJei(final J jeiType) {
        return this.toCommandStringFromZen(this.toZenFromJei(jeiType));
    }
    
    default ResourceLocation toRegistryNameFromZen(final Z zenType) {
        return this.toRegistryNameFromJei(this.toJeiFromZen(zenType));
    }
}
