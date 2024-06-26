package one.hyro.duels.events;

import one.hyro.managers.BlockManager;
import one.hyro.managers.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        BlockManager blockManager = new BlockManager();
        GameManager gameManager = GameManager.getInstance();

        if (gameManager.isPlayerInGame(event.getPlayer().getUniqueId()) ) {
            if (blockManager.getBlocks().containsKey(event.getBlock())) event.setCancelled(true);
        }
    }
}
