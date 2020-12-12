package world.cepi.mobextension

import net.minestom.server.entity.EntityType
import net.minestom.server.entity.type.animal.*
import net.minestom.server.entity.type.monster.*

val mobTypeList = listOf(
        EntityCow::class to EntityType.COW,
        EntityCat::class to EntityType.CAT,
        EntityPanda::class to EntityType.PANDA,
        EntityLlama::class to EntityType.LLAMA,
        EntityBee::class to EntityType.BEE,
        EntityChicken::class to EntityType.CHICKEN,
        EntityZombie::class to EntityType.ZOMBIE,
        EntityBlaze::class to EntityType.BLAZE,
        EntityGuardian::class to EntityType.GUARDIAN,
        EntityEndermite::class to EntityType.ENDERMITE,
        EntityMooshroom::class to EntityType.MOOSHROOM,
        EntityFox::class to EntityType.FOX,
        EntityPhantom::class to EntityType.PHANTOM,
        EntityOcelot::class to EntityType.OCELOT,
        EntityZombifiedPiglin::class to EntityType.ZOMBIFIED_PIGLIN,
        EntityPolarBear::class to EntityType.POLAR_BEAR,
        EntityGhast::class to EntityType.GHAST,
        EntitySpider::class to EntityType.SPIDER,
        EntityWitch::class to EntityType.WITCH,
        EntitySlime::class to EntityType.SLIME
)