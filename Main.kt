//arraylist che mi serviranno in tutto il programma
var Domande= ArrayList<String>()
var Risposte= ArrayList<String>()
var RisposteGiuste= ArrayList<Int>()

fun main() {
    //dichiarazione variabili
    val admin="admin"
    val passadmin="capo1234"
    var nomeutente:String
    var password:String
    var checkadmin:Boolean = true
    val login : Int

    //metto alcune domande di default
    Domande.add("Di che colore era il cavallo bianco di Napoleone?")
    Domande.add("Quanti colori ha l'arcobaleno?")
    Domande.add("Chi è il presidente degli USA?")

    //metto le risposte di default
    Risposte.add("1)Nero \n2)Bianco \n3)Marrone")
    Risposte.add("1)7 \n2)8 \n3)4")
    Risposte.add("1)Trump \n2)Obama \n3)Biden")

    //metto le risposte giuste indicandole solo col numero
    RisposteGiuste.add(2)
    RisposteGiuste.add(1)
    RisposteGiuste.add(3)

    //controllo se ad accedere è un utente qualsiasi o l'admin
    println("Premi 1 se sei un utente, premi 2 se sei l'admin")
    login= readLine()!!.toInt()


    if (login == 1) {   //cao accesso utente
         println("Inserisci il tuo nome: ")
         nomeutente = readLine()!!.toString()
         println("Crea una password: ")
         password = readLine()!!.toString()

         utente()    //fa partire le opzioni dell'utente


    } //fine caso accesso utente
    else if (login == 2) {    //caso accesso admin

         do {    //ciclo che fa inseire nome utente e password all'admin finchè non sono corrette
             println("Inserisci il tuo nome: ")
             nomeutente = readLine()!!.toString()

             println("Inserisci la tua password: ")
             password = readLine()!!.toString()

             //controllo che il nome e la password inserite siano effettivamente quelle dell'admin
             if (nomeutente.equals(admin) && password.equals(passadmin)) {
                 println("nome utente e password corrette")
                 casoadmin()
                 checkadmin = false   //esco dal ciclo
             } else
                 println("nome utente o password errati, inseriscili di nuovo")
         } while (checkadmin)


    }   //fine caso accesso admin

} //chiusura main

//-----funzioni di utilità-----
fun utente(){
    var scelta:Int
    var risposta:Int
    var punteggio = 0


        println("Premi 1 per eseguire il quiz, premi 2 per uscire") //l'utente sceglie se fare il quiz o uscire
        scelta = readLine()!!.toInt()

        if (scelta == 1){

            for(i in 0 until Domande.size){
                println("Domanda ${i+1}: ${Domande[i]}")
                println("Risposte${i+1}: \n ${Risposte[i]}")
                println("---Per indicare la risposta premi 1,2 o 3: ")
                risposta= readLine()!!.toInt()

                if(risposta==RisposteGiuste[i])
                    punteggio++
            }

            //finito il quiz faccio vedere all'utente il punteggio
            println("Quiz finito: il tuo punteggio è stato: ${punteggio}")
            return //finito il quiz, l'utente non può rifare il test

        }   //fine quiz
        else if(scelta==2){
            return
        }

} //fine funzione utente

fun casoadmin(){
    var scelta:Int
    var check= true
    var question:String
    var answer:String
    var rightanswer:Int
    var remove:Int

    while(check){
        println("Premi (1) per aggiungere una domanda, (2) per rimuoverne una, (3) per visualizzare domande e risposte o (4) per uscire ")
        scelta = readLine()!!.toInt()

        when (scelta) {
            1 -> {  //l'admin vuole aggiungere una domanda
                println("Inserisci la nuova domanda: \n")
                question= readLine()!!.toString()
                Domande.add(question)

                println("Inserisci 3 risposte: \n")
                answer= readLine()!!.toString()
                Risposte.add(answer)

                println("Inserisci la risposta corretta (1,2 o 3): ")
                rightanswer= readLine()!!.toInt()
                RisposteGiuste.add(rightanswer)


            }
            2 -> {  //l'admin vuole rimuovere una domanda
                println("Queste sono le domande: \n")
                for(i in 0 until Domande.size){
                    println("${i+1}) ${Domande[i]}")
                }

                println("---Quale domanda vuoi eliminare? Premi l'indice:")
                remove= readLine()!!.toInt()

                Domande.removeAt(remove-1)
                RisposteGiuste.removeAt(remove-1)
                Risposte.removeAt(remove-1)

                println("Il nuovo quiz è:")
                for (j in 0 until Domande.size) {
                    println("Domanda ${j + 1}: ${Domande[j]}")
                    println("Risposte${j + 1}: \n ${Risposte[j]}")

                }

            }
            3 -> {  //l'admin vuole visualizzare domande e risposte
                //visualizzo domande e risposte
                for (i in 0 until Domande.size) {
                    println("Domanda ${i + 1}: ${Domande[i]}")
                    println("Risposte${i + 1}: \n ${Risposte[i]}")

                }
            }
            4 -> {  //l'admin vuole uscire

                check=false
            }

            else -> println("Opzione non valida!")

        }   //fine when

    }

}