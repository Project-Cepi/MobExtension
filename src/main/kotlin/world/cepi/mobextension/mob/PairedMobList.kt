package world.cepi.mobextension.mob

import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.type.animal.EntityCat
import net.minestom.server.entity.type.animal.EntityCow
import kotlin.reflect.KClass

val mobTypeList = listOf<Pair<KClass<out Entity>, EntityType>>(
        EntityCow::class to EntityType.COW,
        EntityCat::class to EntityType.CAT
)