package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import net.minecraft.resources.ResourceLocation;

public class LogBirchQuantumSingularityItem extends QuantumSingularityItemBase {
    public LogBirchQuantumSingularityItem() {
        super(new Properties(),
                QuantumSingularityRegistry.getInstance()
                        .getSingularityById(new ResourceLocation(CuboidMod.MOD_ID, "birch_log")));
    }
}
