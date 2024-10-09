package priv

import java.io.File


class Leadboard(val path: String) {

    var playerList = mutableListOf<Player>()

    //Wczytuje graczy do listy z pliku, jeśli taki nie istnieje tworzy nowy
    fun read(){
        if(!File(path).exists()){
            File(path).createNewFile()
        }

        playerList.clear()
        File(path).bufferedReader().forEachLine { line ->
            playerList.add(Player(line.split(":").first(), line.split(":").last().toInt(), false))
        }
    }

    //Usuwamy dane gracza z listy i dodajemy nowe dane, później sortujemy wynikiem w dół
    fun update(player: Player){

        var i = 0
        while(i < playerList.size){
            if(player.name == playerList[i].name){
                playerList.removeAt(i)
                break
            }
            i++
        }
        playerList.add(player)
        playerList.sortByDescending{it.score}

    }

    //Zapis danych z listy do pliku
    fun save() {

        File(path).delete()
        File(path).createNewFile()
        File(path).bufferedWriter().use { out ->
            playerList.forEach { player ->
                out.appendLine("${player.name}:${player.score}")
            }
        }
    }

    //Wyświetlamy graczy z listy
    fun display(){

        for(i in 0..playerList.size-1){
        println("${i + 1}. ${playerList[i].name} with score ${playerList[i].score}")
        }
    }

    //Sprawdzamy czy gracz jest nowy, jeśli nie to przypisujemy zapisany wcześniej wynik
    fun check(player: Player){
        for(i in 0..playerList.size-1){
            if(player.name == playerList[i].name){
                player.isNew = false
                player.score = playerList[i].score
            }
        }
    }

}