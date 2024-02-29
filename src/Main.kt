import kotlin.random.Random
fun generateSecretNumber(): String { // функция для генерации тайного числа компьютером
    val digits = mutableListOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    digits.shuffle()
    return digits.subList(0, 4).joinToString("")
}
data class GuessResult(val bulls: Int, val cows: Int) // класс для хранения результата оценки догадки
fun enterGuess(): String { // функция для ввода догадки пользователя
    print("Введите вашу догадку - это 4 цифры без повторений: ")
    return readLine()?.take(4) ?: ""
}
fun evaluateGuess(secret: String, guess: String): GuessResult { // функция для оценки догадки и возврата результата в виде объекта GuessResult
    var bulls = 0
    var cows = 0
    for (i in secret.indices) { // подсчет быков и коров
        if (secret[i] == guess[i]) {
            bulls++
        } else if (secret.contains(guess[i])) {
            cows++
        }
    }
    return GuessResult(bulls, cows)
}
fun main() {
    println("Игра 'Быки и коровы'!")
    println("Компьютер загадал 4-значное число. Попробуйте отгадать!")
    val secretNumber = generateSecretNumber() // генерация тайного числа
    var attempts = 0 // счетчик попыток пользователя
    while (attempts < 10) { //цикл не более 10 попыток
        val guess = enterGuess() // ввод пользователем догадки
        val result = evaluateGuess(secretNumber, guess) // оценка догадки и вывод результата
        println("Быки = ${result.bulls}, Коровы = ${result.cows}")
        attempts++
        if (result.bulls == 4) { // проверка на угаданное число
            println("Вы отгадали число '$secretNumber' за $attempts попыток.")
            break
        }
    }
    if (attempts == 10) {
        println("Вы не смогли отгадать число '$secretNumber' за 10 попыток. Попробуйте еще раз.")
    }
}
