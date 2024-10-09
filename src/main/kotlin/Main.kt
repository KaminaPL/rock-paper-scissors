package priv


fun main() {

    //Tworzymy nowego gracza
    println("Please enter your name")
    var name: String?
    name = readLine()?.trim()

    println("Hello $name!")
    val player: Player = Player(name, 0, true)

    val leadboard = Leadboard("/home/bartosz/code/IdeaProjects/MobileApp/rock-paper-scissors/src/main/resources" +
            "/leadboard.txt")

    leadboard.read()
    leadboard.check(player)           //Sprawdzamy, czy gracz już przypadkiem nie istnieje
    if(player.isNew == false) {
        println("Good to see you again " + player.name + ", your current score is " + player.score)
    }

    println("How many rounds?")
    var rounds: Int = 0
    while(rounds == 0 || rounds > 10){
        rounds = readLine()?.toIntOrNull() ?: 0
    }

    //Rozpoczynamy rozgrywkę
    println("\n--+ Let the game begin +-- \n")
    val game: Game = Game(rounds, player)

    while(game.rounds != 0) {

        println("\nChoose rock, paper or scissors")
        game.selectChoice()
        game.compare()
        game.rounds--
        Thread.sleep(2_000)
    }

    println("History: ")
    for(i in 0..(game.listedRounds.size-1)){
        println("\n--+ Round:" + (i+1) + " +--" + "\n--+ Score:" + game.listedRounds[i] + " +--")
        Thread.sleep(1_000)
    }

    println("\n--+ Thank you for playing, check your score in game folder +-- \n")
    Thread.sleep(2_000)

    leadboard.update(player)
    println("\n--+ LEADBOARD +--")
    Thread.sleep(2_000)
    leadboard.display()
    leadboard.save()


}
