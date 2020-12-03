package world.cepi.mobextension.mob

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
    EntityPhantom:: class to EntityType.PHANTOM
)