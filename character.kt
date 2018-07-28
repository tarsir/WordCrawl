interface BaseCharacter {
    val name: String
    var currentHitpoints: Int
    var maxHitpoints: Int
    var power: Int
    var baseAccuracy: Int
    val actionHandler: ActionHandler
}

class Character(override val name: String,
                override var currentHitpoints: Int,
                override var maxHitpoints: Int,
                override var power: Int,
                override var baseAccuracy: Int,
                override val actionHandler: ActionHandler
) : BaseCharacter {
    fun combatStatus() = "${name}: ${currentHitpoints} (${maxHitpoints})"
    fun isDead() = if (currentHitpoints <= 0) true else false
}