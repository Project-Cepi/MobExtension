package world.cepi.mobextension

import net.minestom.server.entity.EntityType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

infix fun EntityType.withData(material: Material) = EntityData(this to material)

/** Represents extra data appended to an [EntityType], for displaying as an [ItemStack]. */
inline class EntityData(val pair: Pair<EntityType, Material>) {

    val material: Material
        get() = pair.second

    val type: EntityType
        get() = pair.first

    val displayName: String
        get() = pair.first.name.toLowerCase().split("_").joinToString(" ") { it.capitalize() }

    companion object {
        /** [EntityType]s to their respective [Material]. */
        val mobTypeList = hashSetOf<EntityData>(
            EntityType.COW withData Material.COW_SPAWN_EGG,
            EntityType.CAT withData Material.CAT_SPAWN_EGG,
            EntityType.PANDA withData Material.PANDA_SPAWN_EGG,
            EntityType.LLAMA withData Material.LLAMA_SPAWN_EGG,
            EntityType.TRADER_LLAMA withData Material.TRADER_LLAMA_SPAWN_EGG,
            EntityType.WANDERING_TRADER withData Material.WANDERING_TRADER_SPAWN_EGG,
            EntityType.BEE withData Material.BEE_SPAWN_EGG,
            EntityType.CHICKEN withData Material.CHICKEN_SPAWN_EGG,
            EntityType.ZOMBIE withData Material.ZOMBIE_SPAWN_EGG,
            EntityType.BLAZE withData Material.BLAZE_SPAWN_EGG,
            EntityType.GUARDIAN withData Material.GUARDIAN_SPAWN_EGG,
            EntityType.ENDERMITE withData Material.ENDERMITE_SPAWN_EGG,
            EntityType.MOOSHROOM withData Material.MOOSHROOM_SPAWN_EGG,
            EntityType.FOX withData Material.FOX_SPAWN_EGG,
            EntityType.PHANTOM withData Material.PHANTOM_SPAWN_EGG,
            EntityType.OCELOT withData Material.OCELOT_SPAWN_EGG,
            EntityType.ZOMBIFIED_PIGLIN withData Material.ZOMBIFIED_PIGLIN_SPAWN_EGG,
            EntityType.POLAR_BEAR withData Material.POLAR_BEAR_SPAWN_EGG,
            EntityType.GHAST withData Material.GHAST_SPAWN_EGG,
            EntityType.SPIDER withData Material.SPIDER_SPAWN_EGG,
            EntityType.WITCH withData Material.WITCH_SPAWN_EGG,
            EntityType.SLIME withData Material.SLIME_SPAWN_EGG,
            EntityType.CREEPER withData Material.CREEPER_SPAWN_EGG,
            EntityType.HORSE withData Material.HORSE_SPAWN_EGG,
            EntityType.SKELETON_HORSE withData Material.SKELETON_HORSE_SPAWN_EGG,
            EntityType.ZOMBIE_HORSE withData Material.ZOMBIE_HORSE_SPAWN_EGG,
            EntityType.ARMOR_STAND withData Material.ARMOR_STAND,
            EntityType.ITEM withData Material.PAPER,
            EntityType.ITEM_FRAME withData Material.ITEM_FRAME,
            EntityType.PAINTING withData Material.PAINTING,
            EntityType.FISHING_BOBBER withData Material.FISHING_ROD,
            EntityType.ZOGLIN withData Material.ZOGLIN_SPAWN_EGG,
            EntityType.WITHER_SKULL withData Material.WITHER_SKELETON_SKULL,
            EntityType.WITHER_SKELETON withData Material.WITHER_SKELETON_SPAWN_EGG,
            EntityType.WITHER withData Material.NETHER_STAR,
            EntityType.EGG withData Material.EGG,
            EntityType.TRIDENT withData Material.TRIDENT,
            EntityType.POTION withData Material.SPLASH_POTION,
            EntityType.ENDER_PEARL withData Material.ENDER_PEARL,
            EntityType.ENDER_DRAGON withData Material.DRAGON_BREATH,
            EntityType.ARROW withData Material.ARROW,
            EntityType.COD withData Material.COD,
            EntityType.DOLPHIN withData Material.DOLPHIN_SPAWN_EGG,
            EntityType.END_CRYSTAL withData Material.END_CRYSTAL,
            EntityType.FALLING_BLOCK withData Material.RABBIT_FOOT,
            EntityType.GIANT withData Material.ROTTEN_FLESH,
            EntityType.LIGHTNING_BOLT withData Material.BLAZE_ROD,
            EntityType.BAT withData Material.BAT_SPAWN_EGG,
            EntityType.EVOKER_FANGS withData Material.EVOKER_SPAWN_EGG,
            EntityType.EYE_OF_ENDER withData Material.ENDER_EYE,
            EntityType.PLAYER withData Material.DIAMOND_PICKAXE
        )

        fun findByMaterial(material: Material) = this.mobTypeList.firstOrNull { it.material ==material }
        fun findByType(type: EntityType) = this.mobTypeList.firstOrNull { it.type == type }
    }

}

val ItemStack.entityData: EntityData?
    get() = EntityData.findByMaterial(this.material)