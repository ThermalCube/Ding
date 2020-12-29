package de.thermalcube.ding;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.Tooltip;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Config(name = "ding")
public class ModConfig implements ConfigData {

    @Tooltip(count = 5)
    public String name = "entity.experience_orb.pickup";

    @Tooltip
    public float pitch = 1.0f;

    public ModConfig(){
    }

    @Override
    public void validatePostLoad() throws ValidationException {
        if(name.isEmpty() || !Registry.SOUND_EVENT.containsId(new Identifier(name)))
            name = "entity.experience_orb.pickup";

        if(pitch <= 0.0f || pitch >= 10.0f)
            pitch = 1.0f;
    }
}
