/**
 * Created by sthar on 12/10/2017.
 */

import java.util.Random

val random = Random()

fun main(args: Array<String>) {
    var dude = Character("Dude", 1, 20, 2, 1, PlayerActions())
    var baddy = Character("Baddy", 1, 8, 1, 3, ComputerActions())

    while (true) {
        fightStart(dude, baddy)
        break
    }
}
