package world.cepi.mobextension

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.type.animal.*
import net.minestom.server.entity.type.monster.*
import net.minestom.server.item.Material
import kotlin.reflect.KClass

val mobTypeList: List<Pair<Pair<KClass<out EntityCreature>, EntityType>, Material>> = listOf(
        EntityCow::class to EntityType.COW to Material.COW_SPAWN_EGG,
        EntityCat::class to EntityType.CAT to Material.CAT_SPAWN_EGG,
        EntityPanda::class to EntityType.PANDA to Material.PANDA_SPAWN_EGG,
        EntityLlama::class to EntityType.LLAMA to Material.LLAMA_SPAWN_EGG,
        EntityBee::class to EntityType.BEE to Material.BEE_SPAWN_EGG,
        EntityChicken::class to EntityType.CHICKEN to Material.CHICKEN_SPAWN_EGG,
        EntityZombie::class to EntityType.ZOMBIE to Material.ZOMBIE_SPAWN_EGG,
        EntityBlaze::class to EntityType.BLAZE to Material.BLAZE_SPAWN_EGG,
        EntityGuardian::class to EntityType.GUARDIAN to Material.GUARDIAN_SPAWN_EGG,
        EntityEndermite::class to EntityType.ENDERMITE to Material.ENDERMITE_SPAWN_EGG,
        EntityMooshroom::class to EntityType.MOOSHROOM to Material.MOOSHROOM_SPAWN_EGG,
        EntityFox::class to EntityType.FOX to Material.FOX_SPAWN_EGG,
        EntityPhantom::class to EntityType.PHANTOM to Material.PHANTOM_SPAWN_EGG,
        EntityOcelot::class to EntityType.OCELOT to Material.OCELOT_SPAWN_EGG,
        EntityZombifiedPiglin::class to EntityType.ZOMBIFIED_PIGLIN to Material.ZOMBIFIED_PIGLIN_SPAWN_EGG,
        EntityPolarBear::class to EntityType.POLAR_BEAR to Material.POLAR_BEAR_SPAWN_EGG,
        EntityGhast::class to EntityType.GHAST to Material.GHAST_SPAWN_EGG,
        EntitySpider::class to EntityType.SPIDER to Material.SPIDER_SPAWN_EGG,
        EntityWitch::class to EntityType.WITCH to Material.WITCH_SPAWN_EGG,
        EntitySlime::class to EntityType.SLIME to Material.SLIME_SPAWN_EGG
)