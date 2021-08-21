package me.kodysimpson.packetstutorialprotocollib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketsTutorialProtocolLib extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this, ListenerPriority.HIGH, PacketType.Play.Server.WORLD_PARTICLES) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                if (event.getPacketType() != PacketType.Play.Server.WORLD_PARTICLES)
                    return;

                if (packet.getNewParticles().read(0).getParticle() == Particle.DAMAGE_INDICATOR)
                    event.setCancelled(true);
            }
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
