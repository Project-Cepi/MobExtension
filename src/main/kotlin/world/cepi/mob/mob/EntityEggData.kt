package world.cepi.mob.mob

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.minestom.server.entity.EntityType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

/** Represents extra data appended to an [EntityType], for displaying as an [ItemStack]. */
enum class EntityEggData(
    val type: EntityType,
    val material: Material,
    val color: TextColor = NamedTextColor.GOLD
) {


    AREA_EFFECT_CLOUD(EntityType.AREA_EFFECT_CLOUD, Material.LEATHER_HORSE_ARMOR),
    ARMOR_STAND(EntityType.ARMOR_STAND, Material.ARMOR_STAND),
    ARROW(EntityType.ARROW, Material.ARROW),
    AXOLOTL(EntityType.AXOLOTL, Material.AXOLOTL_SPAWN_EGG),
    BAT(EntityType.BAT, Material.BAT_SPAWN_EGG),
    BEE(EntityType.BEE, Material.BEE_SPAWN_EGG),
    BLAZE(EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
    BOAT(EntityType.BOAT, Material.OAK_BOAT),
    CAT(EntityType.CAT, Material.CAT_SPAWN_EGG),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG),
    CHEST_MINECART(EntityType.CHEST_MINECART, Material.CHEST_MINECART),
    CHICKEN(EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
    COD(EntityType.COD, Material.COD),
    COMMAND_BLOCK_MINECART(EntityType.COMMAND_BLOCK_MINECART, Material.COMMAND_BLOCK_MINECART),
    COW(EntityType.COW, Material.COW_SPAWN_EGG),
    CREEPER(EntityType.CREEPER, Material.CREEPER_SPAWN_EGG),
    DOLPHIN(EntityType.DOLPHIN, Material.DOLPHIN_SPAWN_EGG),
    DONKEY(EntityType.DONKEY, Material.DONKEY_SPAWN_EGG),
    DRAGON_FIREBALL(EntityType.DRAGON_FIREBALL, Material.DRAGON_BREATH),
    DROWNED(EntityType.DROWNED, Material.DROWNED_SPAWN_EGG),
    EGG(EntityType.EGG, Material.EGG),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, Material.ELDER_GUARDIAN_SPAWN_EGG),
    ENDERMAN(EntityType.ENDERMAN, Material.ENDERMAN_SPAWN_EGG),
    ENDERMITE(EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, Material.DRAGON_BREATH),
    ENDER_PEARL(EntityType.ENDER_PEARL, Material.ENDER_PEARL),
    END_CRYSTAL(EntityType.END_CRYSTAL, Material.END_CRYSTAL),
    EVOKER(EntityType.EVOKER, Material.EVOKER_SPAWN_EGG),
    EVOKER_FANGS(EntityType.EVOKER_FANGS, Material.EVOKER_SPAWN_EGG),
    EXPERIENCE_BOTTLE(EntityType.EXPERIENCE_BOTTLE, Material.EXPERIENCE_BOTTLE),
    EXPERIENCE_ORB(EntityType.EXPERIENCE_ORB, Material.LAPIS_LAZULI),
    EYE_OF_ENDER(EntityType.EYE_OF_ENDER, Material.ENDER_EYE),
    FALLING_BLOCK(EntityType.FALLING_BLOCK, Material.RABBIT_FOOT),
    FIREBALL(EntityType.FIREBALL, Material.FIRE_CHARGE),
    FIREWORK_ROCKET(EntityType.FIREWORK_ROCKET, Material.FIREWORK_ROCKET),
    FISHING_BOBBER(EntityType.FISHING_BOBBER, Material.FISHING_ROD),
    FOX(EntityType.FOX, Material.FOX_SPAWN_EGG),
    FURNACE_MINECART(EntityType.FURNACE_MINECART, Material.FURNACE_MINECART),
    GHAST(EntityType.GHAST, Material.GHAST_SPAWN_EGG),
    GIANT(EntityType.GIANT, Material.ROTTEN_FLESH),
    GLOW_ITEM_FRAME(EntityType.GLOW_ITEM_FRAME, Material.GLOW_ITEM_FRAME),
    GLOW_SQUID(EntityType.GLOW_SQUID, Material.GLOW_SQUID_SPAWN_EGG),
    GOAT(EntityType.GOAT, Material.GOAT_SPAWN_EGG),
    GUARDIAN(EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG),
    HOGLIN(EntityType.HOGLIN, Material.HOGLIN_SPAWN_EGG),
    HOPPER_MINECART(EntityType.HOPPER_MINECART, Material.HOPPER_MINECART),
    HORSE(EntityType.HORSE, Material.HORSE_SPAWN_EGG),
    HUSK(EntityType.HUSK, Material.HUSK_SPAWN_EGG),
    ILLUSIONER(EntityType.ILLUSIONER, Material.MELON_SLICE),
    IRON_GOLEM(EntityType.IRON_GOLEM, Material.PUMPKIN_SEEDS),
    ITEM(EntityType.ITEM, Material.PAPER),
    ITEM_FRAME(EntityType.ITEM_FRAME, Material.ITEM_FRAME),
    LEASH_KNOT(EntityType.LEASH_KNOT, Material.LEAD),
    LIGHTNING_BOLT(EntityType.LIGHTNING_BOLT, Material.BLAZE_ROD),
    LLAMA(EntityType.LLAMA, Material.LLAMA_SPAWN_EGG),
    LLAMA_SPIT(EntityType.LLAMA_SPIT, Material.DIAMOND_HORSE_ARMOR),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, Material.MAGMA_CUBE_SPAWN_EGG),
    MARKER(EntityType.MARKER, Material.IRON_HORSE_ARMOR),
    MINECART(EntityType.MINECART, Material.MINECART),
    MOOSHROOM(EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG),
    MULE(EntityType.MULE, Material.MULE_SPAWN_EGG),
    OCELOT(EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
    PAINTING(EntityType.PAINTING, Material.PAINTING),
    PANDA(EntityType.PANDA, Material.PANDA_SPAWN_EGG),
    PARROT(EntityType.PARROT, Material.PARROT_SPAWN_EGG),
    PHANTOM(EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG),
    PIG(EntityType.PIG, Material.PIG_SPAWN_EGG),
    PIGLIN(EntityType.PIGLIN, Material.PIGLIN_SPAWN_EGG),
    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, Material.PIGLIN_BRUTE_SPAWN_EGG),
    PILLAGER(EntityType.PILLAGER, Material.PILLAGER_SPAWN_EGG),
    PLAYER(EntityType.PLAYER, Material.DIAMOND_PICKAXE),
    POLAR_BEAR(EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG),
    POTION(EntityType.POTION, Material.SPLASH_POTION),
    PUFFERFISH(EntityType.PUFFERFISH, Material.PUFFERFISH),
    RABBIT(EntityType.RABBIT, Material.RABBIT_SPAWN_EGG),
    RAVAGER(EntityType.RAVAGER, Material.RAVAGER_SPAWN_EGG),
    SALMON(EntityType.SALMON, Material.SALMON),
    SHEEP(EntityType.SHEEP, Material.SHEEP_SPAWN_EGG),
    SHULKER(EntityType.SHULKER, Material.SHULKER_SPAWN_EGG),
    SHULKER_BULLET(EntityType.SHULKER_BULLET, Material.SHULKER_SHELL),
    SILVERFISH(EntityType.SILVERFISH, Material.SILVERFISH_SPAWN_EGG),
    SKELETON(EntityType.SKELETON, Material.SKELETON_SPAWN_EGG),
    SKELETON_HORSE(EntityType.SKELETON_HORSE, Material.SKELETON_HORSE_SPAWN_EGG),
    SLIME(EntityType.SLIME, Material.SLIME_SPAWN_EGG),
    SMALL_FIREBALL(EntityType.SMALL_FIREBALL, Material.FIREWORK_STAR),
    SNOWBALL(EntityType.SNOWBALL, Material.SNOWBALL),
    SNOW_GOLEM(EntityType.SNOW_GOLEM, Material.SNOWBALL),
    SPAWNER_MINECART(EntityType.SPAWNER_MINECART, Material.DIAMOND),
    SPECTRAL_ARROW(EntityType.SPECTRAL_ARROW, Material.SPECTRAL_ARROW),
    SPIDER(EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
    SQUID(EntityType.SQUID, Material.SQUID_SPAWN_EGG),
    STRAY(EntityType.STRAY, Material.STRAY_SPAWN_EGG),
    STRIDER(EntityType.STRIDER, Material.STRIDER_SPAWN_EGG),
    TNT(EntityType.TNT, Material.GUNPOWDER),
    TNT_MINECART(EntityType.TNT_MINECART, Material.TNT_MINECART),
    TRADER_LLAMA(EntityType.TRADER_LLAMA, Material.TRADER_LLAMA_SPAWN_EGG),
    TRIDENT(EntityType.TRIDENT, Material.TRIDENT),
    TROPICAL_FISH(EntityType.TROPICAL_FISH, Material.TROPICAL_FISH_SPAWN_EGG),
    TURTLE(EntityType.TURTLE, Material.TURTLE_SPAWN_EGG),
    VEX(EntityType.VEX, Material.VEX_SPAWN_EGG),
    VILLAGER(EntityType.VILLAGER, Material.VILLAGER_SPAWN_EGG),
    VINDICATOR(EntityType.VINDICATOR, Material.VINDICATOR_SPAWN_EGG),
    WANDERING_TRADER(EntityType.WANDERING_TRADER, Material.WANDERING_TRADER_SPAWN_EGG),
    WITCH(EntityType.WITCH, Material.WITCH_SPAWN_EGG),
    WITHER(EntityType.WITHER, Material.NETHER_STAR),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, Material.STONE_SWORD),
    WITHER_SKULL(EntityType.WITHER_SKULL, Material.WITHER_SKELETON_SPAWN_EGG),
    WOLF(EntityType.WOLF, Material.WOLF_SPAWN_EGG),
    ZOGLIN(EntityType.ZOGLIN, Material.ZOGLIN_SPAWN_EGG),
    ZOMBIE(EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
    ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE, Material.ZOMBIE_HORSE_SPAWN_EGG),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, Material.ZOMBIE_VILLAGER_SPAWN_EGG),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);

    val displayName: String
        get() = type.name().replace("minecraft:", "").lowercase().split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercase)}

    companion object {
        fun findByMaterial(material: Material) = values().firstOrNull { it.material == material }
        fun findByType(type: EntityType) = values().firstOrNull { it.type == type }
    }

}

val ItemStack.entityEggData: EntityEggData?
    get() = EntityEggData.findByMaterial(this.material)