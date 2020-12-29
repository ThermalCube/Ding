package de.thermalcube.ding.mixin;

import de.thermalcube.ding.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

    private static boolean loaded = false;

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Override
    public void init(MinecraftClient client, int width, int height) {
        super.init(client, width, height);

        if(client.inGameHud.getTicks() < 10) return;

        if(loaded) return;

        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        SoundEvent soundEvent = Registry.SOUND_EVENT.get(new Identifier(config.name));
        assert soundEvent != null;

        loaded = true;

        client.getSoundManager().play(PositionedSoundInstance.master(soundEvent, config.pitch));

    }
}
