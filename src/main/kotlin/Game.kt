package priv


class Game(var rounds: Int, val player: Player) {

    var opponentChoice: String? = null
    var playerChoice: String? = null
    val listedRounds: MutableList<Int> = ArrayList()

    //Porównujemy wybór gracza z wyborem przeciwnika
    fun compare(){
        this.randomChoice()
        var set: Int? = null

        when(this.playerChoice){
            "rock" -> set = 0
            "paper" -> set = 1
            "scissors" -> set = 2
        }

        if(set == 0){
            when(this.opponentChoice){
                "paper" -> { println("You Lost! Minus one point." )
                 this.setPlayerPoints(-1)}

                "scissors" -> { println("You Won! One point added.")
                 this.setPlayerPoints(1)}

                "rock" -> { println("Draw! No points added.") }
            }
        }

        if(set == 1){
            when(this.opponentChoice){
                "scissors" -> { println("You Lost! Minus one point." )
                    this.setPlayerPoints(-1)}

                "rock" -> { println("You Won! One point added.")
                    this.setPlayerPoints(1)}

                "paper" -> { println("Draw! No points added.") }
            }
        }

        if(set == 2){
            when(this.opponentChoice){
                "rock" -> { println("You Lost! Minus one point." )
                    this.setPlayerPoints(-1)}

                "paper" -> { println("You Won! One point added.")
                    this.setPlayerPoints(1)}

                "scissors" -> { println("Draw! No points added.") }
            }
        }

        println("Your choice: ${this.playerChoice} \nOpponent's choice: ${this.opponentChoice} \n")

        this.listedRounds.add(player.score)
    }

    fun selectChoice(){

        LOOP@ while(true) {
            var choice: String? = readLine()?.trim()
            playerChoice = choice

            when (choice) {
                "rock" -> { playerChoice = "rock"
                    break@LOOP }

                "paper" -> { playerChoice = "paper"
                    break@LOOP}

                "scissors" -> { playerChoice = "scissors"
                    break@LOOP}

                else -> { println("Please type it again..")
                    continue@LOOP}

            }
        }
    }


    fun setPlayerPoints(value: Int){
        this.player.score += value
    }

    fun randomChoice() {

        var choices: List<String> = listOf("rock","paper","scissors")
        this.opponentChoice = choices.random()
    }


}