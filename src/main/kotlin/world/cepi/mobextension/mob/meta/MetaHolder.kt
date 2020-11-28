package world.cepi.mobextension.mob.meta

interface MetaHolder {

    val metas: MutableList<MobMeta<*>>

    fun addMeta(meta: MobMeta<*>) = metas.add(meta)

}