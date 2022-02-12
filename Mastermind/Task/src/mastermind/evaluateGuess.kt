package mastermind

import java.lang.Integer.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0

    var cekSecret   = secret
    var cekGuess    = guess

    for ((posisi, nilai) in guess.withIndex()){
        when(nilai){
            secret[posisi] -> {
                rightPosition++
                cekGuess = cekGuess.replaceFirst(nilai, '0')
                if (secret[posisi] == cekSecret[posisi]){
                    cekSecret = cekSecret.replaceFirst(nilai, '0')
                }
                else { wrongPosition-- }
            }
            else -> {
                wrongPosition += min(cekSecret.count { it == nilai }, cekGuess.count { it == nilai })
                cekSecret = cekSecret.replace(nilai,'0')
            }
        }
    }
    return Evaluation(rightPosition, wrongPosition)
}
