package world.cepi.mob.mob

import net.minestom.server.entity.EntityType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

/** Represents extra data appended to an [EntityType], for displaying as an [ItemStack]. */
enum class EntityData(val type: EntityType, val material: Material) {

    COW(EntityType.COW, Material.COW_SPAWN_EGG),
    CAT(EntityType.CAT, Material.CAT_SPAWN_EGG),
    PANDA(EntityType.PANDA, Material.PANDA_SPAWN_EGG),
    LLAMA(EntityType.LLAMA, Material.LLAMA_SPAWN_EGG),
    TRADER_LLAMA(EntityType.TRADER_LLAMA, Material.TRADER_LLAMA_SPAWN_EGG),
    WANDERING_TRADER(EntityType.WANDERING_TRADER, Material.WANDERING_TRADER_SPAWN_EGG),
    BEE(EntityType.BEE, Material.BEE_SPAWN_EGG),
    CHICKEN(EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
    ZOMBIE(EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
    BLAZE(EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
    GUARDIAN(EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG),
    ENDERMITE(EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG),
    MOOSHROOM(EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG),
    FOX(EntityType.FOX, Material.FOX_SPAWN_EGG),
    PHANTOM(EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG),
    OCELOT(EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG),
    POLAR_BEAR(EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG),
    GHAST(EntityType.GHAST, Material.GHAST_SPAWN_EGG),
    SPIDER(EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
    WITCH(EntityType.WITCH, Material.WITCH_SPAWN_EGG),
    SLIME(EntityType.SLIME, Material.SLIME_SPAWN_EGG),
    CREEPER(EntityType.CREEPER, Material.CREEPER_SPAWN_EGG),
    HORSE(EntityType.HORSE, Material.HORSE_SPAWN_EGG),
    SKELETON_HORSE(EntityType.SKELETON_HORSE, Material.SKELETON_HORSE_SPAWN_EGG),
    ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE, Material.ZOMBIE_HORSE_SPAWN_EGG),
    ARMOR_STAND(EntityType.ARMOR_STAND, Material.ARMOR_STAND),
    ITEM(EntityType.ITEM, Material.PAPER),
    ITEM_FRAME(EntityType.ITEM_FRAME, Material.ITEM_FRAME),
    PAINTING(EntityType.PAINTING, Material.PAINTING),
    FISHING_BOBBER(EntityType.FISHING_BOBBER, Material.FISHING_ROD),
    ZOGLIN(EntityType.ZOGLIN, Material.ZOGLIN_SPAWN_EGG),
    WITHER_SKULL(EntityType.WITHER_SKULL, Material.WITHER_SKELETON_SPAWN_EGG),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, Material.STONE_SWORD),
    FIREBALL(EntityType.FIREBALL, Material.FIRE_CHARGE),
    WITHER(EntityType.WITHER, Material.NETHER_STAR),
    EGG(EntityType.EGG, Material.EGG),
    TRIDENT(EntityType.TRIDENT, Material.TRIDENT),
    POTION(EntityType.POTION, Material.SPLASH_POTION),
    ENDER_PEARL(EntityType.ENDER_PEARL, Material.ENDER_PEARL),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, Material.DRAGON_BREATH),
    ARROW(EntityType.ARROW, Material.ARROW),
    COD(EntityType.COD, Material.COD),
    DOLPHIN(EntityType.DOLPHIN, Material.DOLPHIN_SPAWN_EGG),
    END_CRYSTAL(EntityType.END_CRYSTAL, Material.END_CRYSTAL),
    RABBIT(EntityType.RABBIT, Material.RABBIT_SPAWN_EGG),
    FALLING_BLOCK(EntityType.FALLING_BLOCK, Material.RABBIT_FOOT),
    GIANT(EntityType.GIANT, Material.ROTTEN_FLESH),
    LIGHTNING_BOLT(EntityType.LIGHTNING_BOLT, Material.BLAZE_ROD),
    BAT(EntityType.BAT, Material.BAT_SPAWN_EGG),
    EVOKER_FANGS(EntityType.EVOKER_FANGS, Material.EVOKER_SPAWN_EGG),
    EYE_OF_ENDER(EntityType.EYE_OF_ENDER, Material.ENDER_EYE),
    PLAYER(EntityType.PLAYER, Material.DIAMOND_PICKAXE),
    MINECART(EntityType.MINECART, Material.MINECART),
    SNOW_GOLEM(EntityType.SNOW_GOLEM, Material.SNOWBALL),
    SQUID(EntityType.SQUID, Material.SQUID_SPAWN_EGG),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, Material.ZOMBIE_VILLAGER_SPAWN_EGG),
    PUFFERFISH(EntityType.PUFFERFISH, Material.PUFFERFISH),
    TNT(EntityType.TNT, Material.GUNPOWDER),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, Material.ELDER_GUARDIAN_SPAWN_EGG),
    BOAT(EntityType.BOAT, Material.OAK_BOAT),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG),
    SHEEP(EntityType.SHEEP, Material.SHEEP_SPAWN_EGG);
    
    val displayName: String
        get() = type.name.lowercase().split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercase)}

    companion object {
        fun findByMaterial(material: Material) = values().firstOrNull { it.material == material }
        fun findByType(type: EntityType) = values().firstOrNull { it.type == type }
    }

}

val ItemStack.entityData: EntityData?
    get() = EntityData.findByMaterial(this.material)