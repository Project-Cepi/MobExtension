package world.cepi.mobextension.commands

object ArrayOperations {

    val insert = { item: Any, arr: MutableList<Any>, index: Int -> arr.add(index, item) }
    val remove = { arr: MutableList<Any>, index: Int -> arr.removeAt(index) }

}
