package world.cepi.mobextension

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.type.animal.*
import net.minestom.server.entity.type.monster.*
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import kotlin.reflect.KClass

data class EntityData(val clazz: KClass<out EntityCreature>, val type: EntityType, val material: Material) {

    companion object {
        val mobTypeList = listOf(
                EntityData(EntityCow::class, EntityType.COW, Material.COW_SPAWN_EGG),
                EntityData(EntityCat::class, EntityType.CAT , Material.CAT_SPAWN_EGG),
                EntityData(EntityPanda::class, EntityType.PANDA, Material.PANDA_SPAWN_EGG),
                EntityData(EntityLlama::class, EntityType.LLAMA, Material.LLAMA_SPAWN_EGG),
                EntityData(EntityBee::class, EntityType.BEE, Material.BEE_SPAWN_EGG),
                EntityData(EntityChicken::class, EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
                EntityData(EntityZombie::class, EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
                EntityData(EntityBlaze::class, EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
                EntityData(EntityGuardian::class, EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG),
                EntityData(EntityEndermite::class, EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG),
                EntityData(EntityMooshroom::class, EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG),
                EntityData(EntityFox::class, EntityType.FOX, Material.FOX_SPAWN_EGG),
                EntityData(EntityPhantom::class, EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG),
                EntityData(EntityOcelot::class, EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
                EntityData(EntityZombifiedPiglin::class, EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG),
                EntityData(EntityPolarBear::class, EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG),
                EntityData(EntityGhast::class, EntityType.GHAST, Material.GHAST_SPAWN_EGG),
                EntityData(EntitySpider::class, EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
                EntityData(EntityWitch::class, EntityType.WITCH, Material.WITCH_SPAWN_EGG),
                EntityData(EntitySlime::class, EntityType.SLIME, Material.SLIME_SPAWN_EGG)
        )

        fun findByMaterial(material: Material) = this.mobTypeList.firstOrNull { it.material ==material }
        fun findByType(type: EntityType) = this.mobTypeList.firstOrNull { it.type == type }
        fun findByClass(clazz: KClass<out EntityCreature>) = this.mobTypeList.firstOrNull { it.clazz == clazz }
    }

}

val ItemStack.entityData: EntityData?
    get() = EntityData.findByMaterial(this.material)