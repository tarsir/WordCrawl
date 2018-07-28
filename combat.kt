enum class AttackResult {
    SUCCESS,
    MISS,
    DO_NOTHING
}

enum class CombatResult {
    VICTORY,
    DEFEAT,
    ONGOING,
    DRAW
}

enum class CombatInputs(val input: Int) {
    PUNCH(1),
    NOTHING(2)
}

abstract class CombatAction {
    abstract val actor: Character
    abstract val target: Character?

    abstract fun executeAction(): AttackResult
}

class Punch(override val actor: Character, override val target: Character) : CombatAction() {
    override fun executeAction(): AttackResult {
        return attack(actor, target)
    }
}

class Hold(override val actor: Character, override val target: Character? = null) : CombatAction() {
    override fun executeAction(): AttackResult {
        return doNothing()
    }
}

fun attack(attacker: Character, defender: Character): AttackResult {
    val hitOdds: Double = (attacker.baseAccuracy)/(attacker.baseAccuracy + 1.0)
    val randomNum = random.nextDouble()
    if (hitOdds > randomNum) {
        defender.currentHitpoints -= attacker.power
        return AttackResult.SUCCESS
    }
    else {
        return AttackResult.MISS
    }
}

fun doNothing(): AttackResult {
    return AttackResult.DO_NOTHING
}

fun evalCombatResult(player: Character, baddy: Character): CombatResult {
    if (player.isDead() && !baddy.isDead()) {
        return CombatResult.DEFEAT
    } else if (!player.isDead() && baddy.isDead()) {
        return CombatResult.VICTORY
    } else if (player.isDead() && baddy.isDead()) {
        return CombatResult.DRAW
    }
    return CombatResult.ONGOING
}

fun printCombatResult(result: CombatResult) {
    if (result == CombatResult.DRAW) {
        println("Well, you're dead, but so is the enemy...so...nice?")
    } else if (result == CombatResult.VICTORY) {
        println("Congratulations, you won!")
    } else if (result == CombatResult.DEFEAT) {
        println("You lost the fight :(")
    }
}

fun printAttackResult(result: AttackResult, offense: Character, defense: Character) {
    if (result == AttackResult.SUCCESS) {
        println("${offense.name} hit ${defense.name} for ${offense.power}!")
    } else if (result == AttackResult.DO_NOTHING) {
        println("${offense.name} sat back and waited!")
    } else {
        println("${offense.name} took a swing at ${defense.name}, but missed!")
    }
}

fun fightStart(char1: Character, char2: Character) {
    println("Fight between ${char1.name} and ${char2.name} begins!")
    var combatResult: CombatResult = evalCombatResult(char1, char2)

    while (combatResult == CombatResult.ONGOING) {
        if (!doCombatRoundForCharacter(char1, char2)) continue
        doCombatRoundForCharacter(char2, char1)
        printCombatStatuses(char1, char2)

        combatResult = evalCombatResult(char1, char2)
    }

    printCombatResult(combatResult)
}

fun doCombatRoundForCharacter(char1: Character, char2: Character): Boolean {
    var combatAction = char1.actionHandler.getCombatAction(char1, char2)
    if (combatAction == null) {
        return false
    }
    val attackResult = combatAction.executeAction()
    printAttackResult(attackResult, char1, char2)
    return true
}

fun printCombatStatuses(char1: Character, char2: Character) {
    println(char1.combatStatus())
    println(char2.combatStatus())
}