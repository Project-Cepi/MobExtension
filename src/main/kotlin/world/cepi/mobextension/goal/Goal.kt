package world.cepi.mobextension.goal

interface Goal {
    fun shouldStart(): Boolean
    fun start()

    fun tick() = Unit

    fun shouldEnd(): Boolean
    fun end()
}