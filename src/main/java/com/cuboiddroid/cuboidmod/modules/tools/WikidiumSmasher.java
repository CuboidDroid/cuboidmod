package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModItemTiers;

public class WikidiumSmasher extends SmasherBase {
    public WikidiumSmasher() {
        super(ModItemTiers.WIKIDIUM,
                Config.wikidiumSmasherAttackDamage.get(),
                Config.wikidiumSmasherAttackSpeed.get(),
                new Properties()
                        .defaultDurability(Config.wikidiumSmasherDurability.get())
                        );
    }
}
