abstract class ActionHandler {
    abstract fun getCombatAction(actor: Character, target: Character): CombatAction?
}

class PlayerActions : ActionHandler() {
    override fun getCombatAction(actor: Character, target: Character): CombatAction? {
        println("Will you punch?")
        println("1) punch")
        println("2) nah")
        val input = readLine()?.toInt() ?: return null
        when (input) {
            1 -> {
                return Punch(actor, target)
            }
            2 -> return Hold(actor)
            else -> {
                throw InputException("wrong")
            }
        }

    }
}

class ComputerActions : ActionHandler() {
    override fun getCombatAction(actor: Character, target: Character): CombatAction? {
        val action = random.nextInt(2) + 1
        println("Computer chose action ${action}")
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