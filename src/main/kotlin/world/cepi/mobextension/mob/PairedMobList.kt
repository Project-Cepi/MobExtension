package world.cepi.mobextension.mob

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.type.animal.EntityCat
import net.minestom.server.entity.type.animal.EntityCow
import kotlin.reflect.KClass

val mobTypeList = listOf<Pair<KClass<out EntityCreature>, EntityType>>(
        EntityCow::class to EntityType.COW,
        EntityCat::class to EntityType.CAT
)