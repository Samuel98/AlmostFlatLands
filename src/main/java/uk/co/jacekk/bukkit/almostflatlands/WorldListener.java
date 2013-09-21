package uk.co.jacekk.bukkit.almostflatlands;

import net.minecraft.server.v1_6_R3.WorldData;
import net.minecraft.server.v1_6_R3.WorldServer;
import net.minecraft.server.v1_6_R3.WorldType;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.WorldInitEvent;

import uk.co.jacekk.bukkit.almostflatlands.generator.ChunkProvider;
import uk.co.jacekk.bukkit.baseplugin.event.BaseListener;
import uk.co.jacekk.bukkit.baseplugin.util.ReflectionUtils;

public class WorldListener extends BaseListener<AlmostFlatLands> {
	
	public WorldListener(AlmostFlatLands plugin){
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onWorldInit(WorldInitEvent event){
		World world = event.getWorld();
		
		if (world.getGenerator() instanceof ChunkProvider){
			WorldServer worldServer = ((CraftWorld) world).getHandle();
			
			try{
				ReflectionUtils.setFieldValue(WorldData.class, "type", worldServer.worldData, WorldType.FLAT);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
