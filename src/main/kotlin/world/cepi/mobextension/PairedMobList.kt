package world.cepi.mobextension

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.type.animal.*
import net.minestom.server.entity.type.monster.*
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import kotlin.reflect.KClass

data class EntityData(val type: EntityType, val material: Material) {

    companion object {
        val mobTypeList = hashSetOf(
                EntityData(EntityType.COW, Material.COW_SPAWN_EGG),
                EntityData(EntityType.CAT , Material.CAT_SPAWN_EGG),
                EntityData(EntityType.PANDA, Material.PANDA_SPAWN_EGG),
                EntityData(EntityType.LLAMA, Material.LLAMA_SPAWN_EGG),
                EntityData(EntityType.BEE, Material.BEE_SPAWN_EGG),
                EntityData(EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
                EntityData(EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
                EntityData(EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
                EntityData(EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG),
                EntityData(EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG),
                EntityData(EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG),
                EntityData(EntityType.FOX, Material.FOX_SPAWN_EGG),
                EntityData(EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG),
                EntityData(EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
                EntityData(EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG),
                EntityData(EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG),
                EntityData(EntityType.GHAST, Material.GHAST_SPAWN_EGG),
                EntityData(EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
                EntityData(EntityType.WITCH, Material.WITCH_SPAWN_EGG),
                EntityData(EntityType.SLIME, Material.SLIME_SPAWN_EGG),
                EntityData(EntityType.CREEPER, Material.CREEPER_SPAWN_EGG),
                EntityData(EntityType.HORSE, Material.HORSE_SPAWN_EGG),
                EntityData(EntityType.SKELETON_HORSE, Material.SKELETON_HORSE_SPAWN_EGG),
                EntityData(EntityType.ZOMBIE_HORSE, Material.ZOMBIE_HORSE_SPAWN_EGG),
                EntityData(EntityType.ARMOR_STAND, Material.ARMOR_STAND),
                EntityData(EntityType.ITEM, Material.PAPER),
                EntityData(EntityType.ITEM_FRAME, Material.ITEM_FRAME),
                EntityData(EntityType.PAINTING, Material.PAINTING)
        )

        fun findByMaterial(material: Material) = this.mobTypeList.firstOrNull { it.material ==material }
        fun findByType(type: EntityType) = this.mobTypeList.firstOrNull { it.type == type }
    }

}

val ItemStack.entityData: EntityData?
    get() = EntityData.findByMaterial(this.material)