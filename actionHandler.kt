abstract class ActionHandler {
    abstract fun getCombatAction(actor: Character, target: Character): CombatAction?
}

class PlayerActions : ActionHandler() {
    override fun getCombatAction(actor: Character, target: Character): CombatAction? {
        println("What will you do? (\"help\" for options)")
        val input = readLine()?.toLowerCase()
        val options = getCombatOptions(actor)
        if (!options.contains(input)) { // ensure the player can do this
            println("That's not quite right...try again")
            return null
        }

        when (input) {
            "punch" -> {
                return Punch(actor, target)
            }
            "hold" -> return Hold(actor)
            "help" -> {
                println(options)
            }
        }
        return null
    }
}

class ComputerActions : ActionHandler() {
    override fun getCombatAction(actor: Character, target: Character): CombatAction? {
        val action = random.nextInt(2) + 1
        when (action) {
            1 -> {
                return Punch(actor, target)
            }
            2 -> return Hold(actor)
            else -> {
                return Hold(actor)
            }
        }
    }
}