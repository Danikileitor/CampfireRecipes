package ml.dnk.campfirerecipes;

import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{
//	Updater
		private PluginDescriptionFile desc = getDescription();
		private static final int ID = 335690;
		private static Updater updater;
		public static boolean update = false;

		private boolean checkUpdate() {
			updater = new Updater(this, ID, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;

			return update;
		}

//	Los ItemStack
	ItemStack dnk_item_carbonvegetal = new ItemStack(Material.CHARCOAL);
	ItemStack dnk_item_hierro = new ItemStack(Material.IRON_INGOT);
	ItemStack dnk_item_oro = new ItemStack(Material.GOLD_INGOT);
	ItemStack dnk_item_cristal = new ItemStack(Material.GLASS);
//	MaterialChoice
	MaterialChoice lana = new MaterialChoice(Tag.WOOL);
//	Los ItemStack BONUS
	ItemStack dnk_item_diamante = new ItemStack(Material.DIAMOND);
	ItemStack dnk_item_globo = new ItemStack(Material.POISONOUS_POTATO);
	ItemStack dnk_item_espadacocida = new ItemStack(Material.STONE_SWORD);
	ItemStack dnk_item_cactuscocido = new ItemStack(Material.CACTUS);
	ItemStack dnk_item_TNT = new ItemStack(Material.TNT);
	
    @Override
    public void onEnable() {
    	Logger log = Bukkit.getLogger();
    	log.info("[CFR] Enjoy playing with fire!");
    	
		if (checkUpdate()) {
			getServer().getConsoleSender()
			.sendMessage("§b[CFR] An update is available, use /cfrupdate to update to the lastest version (from v"
					+ desc.getVersion() + " to v" + updater.getRemoteVersion() + ")");
		}

    	Iterator<Recipe> iteratinator = getServer().recipeIterator();
        while(iteratinator.hasNext()) {
            Recipe esta = iteratinator.next();
            if (esta instanceof FurnaceRecipe) {
                FurnaceRecipe estaEsDeHorno = (FurnaceRecipe) esta;
                //Las cosas
                if (estaEsDeHorno.getInputChoice()!=null) {
                    getServer().addRecipe(new CampfireRecipe(
                            new NamespacedKey(this, estaEsDeHorno.getInput().getType().name().toLowerCase()),
                            estaEsDeHorno.getResult(), estaEsDeHorno.getInputChoice(), estaEsDeHorno.getExperience(), estaEsDeHorno.getCookingTime()));
                }else {
                    getServer().addRecipe(new CampfireRecipe(
                            new NamespacedKey(this, estaEsDeHorno.getInput().getType().name().toLowerCase()),
                            estaEsDeHorno.getResult(), estaEsDeHorno.getInput().getType(), estaEsDeHorno.getExperience(), estaEsDeHorno.getCookingTime()));
                }
            }
        }

/////////////////////////////////////////B-O-N-U-S////////////////////////////////////////////////////////////////////////////////////////////

//	Receta DIAMANTE
		ItemMeta dnk_meta_diamante = dnk_item_diamante.getItemMeta();
//		dnk_meta_diamante.setDisplayName("§4§lPuño Cocido");
		dnk_item_diamante.setItemMeta(dnk_meta_diamante);
//		dnk_meta_diamante.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
		NamespacedKey dnk_key_diamante = new NamespacedKey(this, "dnk_diamante");
		CampfireRecipe dnk_receta_diamante = new CampfireRecipe(dnk_key_diamante, dnk_item_diamante, Material.WHEAT_SEEDS, 100, 200);
getServer().addRecipe(dnk_receta_diamante);

//	Receta PEZ GLOBO
		NamespacedKey dnk_key_globo = new NamespacedKey(this, "dnk_globo");
		CampfireRecipe dnk_receta_globo = new CampfireRecipe(dnk_key_globo, dnk_item_globo, Material.PUFFERFISH, 100, 200);
getServer().addRecipe(dnk_receta_globo);

//	Receta ESPADA COCIDA
		ItemMeta dnk_meta_espadacocida = dnk_item_espadacocida.getItemMeta();
		dnk_meta_espadacocida.setDisplayName("§4§lEspada Cocida");
		dnk_item_espadacocida.setItemMeta(dnk_meta_espadacocida);
		dnk_item_espadacocida.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		NamespacedKey dnk_key_espadacocida = new NamespacedKey(this, "dnk_espadacocida");
		CampfireRecipe dnk_receta_espadacocida = new CampfireRecipe(dnk_key_espadacocida, dnk_item_espadacocida, Material.STONE_SWORD, 100, 200);
getServer().addRecipe(dnk_receta_espadacocida);

//	Receta CACTUS COCIDO
		ItemMeta dnk_meta_cactuscocido = dnk_item_cactuscocido.getItemMeta();
		dnk_meta_cactuscocido.setDisplayName("§4§lCactus Cocido");
		dnk_item_cactuscocido.setItemMeta(dnk_meta_cactuscocido);
		dnk_item_cactuscocido.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
		NamespacedKey dnk_key_cactuscocido = new NamespacedKey(this, "dnk_cactuscocido");
		CampfireRecipe dnk_receta_cactuscocido = new CampfireRecipe(dnk_key_cactuscocido, dnk_item_cactuscocido, Material.CACTUS, 100, 200);
getServer().addRecipe(dnk_receta_cactuscocido);

//	Receta TNT
		ItemMeta dnk_meta_TNT = dnk_item_TNT.getItemMeta();
//		dnk_meta_TNT.setDisplayName("Name");
//		dnk_item_TNT.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
		NamespacedKey dnk_key_TNT = new NamespacedKey(this, "dnk_TNT");
		dnk_meta_TNT.getPersistentDataContainer().set(dnk_key_TNT, PersistentDataType.STRING, "dnk_TNT");
		dnk_item_TNT.setItemMeta(dnk_meta_TNT);
		CampfireRecipe dnk_receta_TNT = new CampfireRecipe(dnk_key_TNT, dnk_item_TNT, Material.GUNPOWDER, 100, 200);
getServer().addRecipe(dnk_receta_TNT);

getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    	Logger log = Bukkit.getLogger();
    	log.info("[CFR] :(");
    	getServer().clearRecipes();
    	getServer().resetRecipes();
    }

    //Explotar TNT de la hoguera
    @EventHandler
    public void onRecetaDone(ItemSpawnEvent e) {
        if (e.getEntity().getItemStack().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "dnk_TNT"), PersistentDataType.STRING)) {
            e.setCancelled(true);
            e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PRIMED_TNT);
            e.getEntity().getWorld().playSound(e.getEntity().getLocation(), "custom.specialsummon", 1, 1);
	   	}
    }
    
    //Para los permisos
	@EventHandler
	public void OnInteraction(PlayerInteractEvent e) {
   	Player p = e.getPlayer();
   	Material m = p.getInventory().getItemInMainHand().getType();
    	if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CAMPFIRE)
    		if (!p.hasPermission("cfr.bonus"))
    			if(m.equals(Material.GUNPOWDER) || m.equals(Material.WHEAT_SEEDS) || m.equals(Material.STONE_SWORD) || m.equals(Material.CACTUS) || m.equals(Material.PUFFERFISH))
    				e.setCancelled(true);
    }
	
	@EventHandler
	public void OnGamemode(PlayerGameModeChangeEvent e) {
	Player p = e.getPlayer();
		if (p.getBedSpawnLocation() != null)
			p.getWorld().spawnEntity(p.getBedSpawnLocation(), EntityType.PRIMED_TNT);
	}
    
   @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
       Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("campfire")) {
            sender.sendMessage("§b[CFR] Be careful with the fire!");
            ItemStack mierda1 = new ItemStack(Material.CAMPFIRE, 1);
            ItemStack mierda2 = new ItemStack(Material.WHEAT_SEEDS, 4);
            p.getInventory().addItem(mierda1);
            p.getInventory().addItem(mierda2);
            return true;
        }
        if (command.getName().equalsIgnoreCase("cfrupdate")) {
        	if (sender.hasPermission("cfr.update")) {
        		if (checkUpdate()) {
        			sender.sendMessage("§b[CFR] Updating CampfireRecipes...");
					updater = new Updater(this, ID, this.getFile(), Updater.UpdateType.DEFAULT, true);
					updater.getResult();
					sender.sendMessage("§b[CFR] Use §e/reload §bto apply changes.");
        		} else {
					sender.sendMessage("§b[CFR] This plugin is already up to date.");
				}
        		
        	} else {sender.sendMessage("§4You don't have permission to use this command.");}
            return true;
        }
        return false;
    }

    
}