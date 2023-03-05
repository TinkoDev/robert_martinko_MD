import kotlin.random.Random

fun main() {
    val generatedNumber = generateNumber()
    println("Willkommen zu unserem ersten mini Game!")
    println("Wer rät der gewinnt! Erraten Sie eine 4-stellige Zahl beliebig oft!")
    println("Weniger als 10 Versuche -> Awesome | Mehr als 10 Versuche -> Good | Mehr als 20 Versuche -> Naja | Mehr als 30 Versuche -> Mehr Üben")
    var guessedNumber: String? = null
    var numAttempts = 0
    while (guessedNumber != generatedNumber) {
        numAttempts++
        guessedNumber = readLine()?.trim()
        if (guessedNumber == null || guessedNumber.length != 4 || !guessedNumber.matches(Regex("\\d+"))) {
            println("Bitte geben Sie eine Zahl die 4-stellig ist! Achten Sie bitte darauf!")
            continue
        }
        val (n, m) = getNumMatches(guessedNumber, generatedNumber)
        println("$n:$m")
    }
    println("Glückwunsch!! Du hast die Zahl erraten und hast $numAttempts Versuche benötigt!")
}

fun generateNumber(): String {
    val digits = mutableListOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    digits.shuffle()
    return digits.subList(0, 4).joinToString("")
}

fun getNumMatches(guess: String, target: String): Pair<Int, Int> {
    var n = 0
    var m = 0
    val guessDigits = guess.toCharArray()
    val targetDigits = target.toCharArray()
    for (i in 0 until 4) {
        if (guessDigits[i] == targetDigits[i]) {
            m++
        }
        if (targetDigits.contains(guessDigits[i])) {
            n++
        }
    }
    n -= m
    return Pair(n, m)
}
