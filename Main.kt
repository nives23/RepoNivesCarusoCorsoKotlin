/** Login: creare e poter accedere ad un profilo utente o admin
 * Utente: Pubblicare annunci sulla bacheca, commentare, comprare e fare logout dall'account
 * Admin: eliminare e modificare annunci sulla bacheca e poter vedere la lista utenti totali
 * e fare logout
 *
 * Difficile: Admin deve poter bannare utenti
 *
 * Il sistema dopo aver eseguito un clock completo (una funzione più end) dovrà riportare
 * alla schermata di login
 */
import java.util.ArrayList

//variabili di sistema
val pwdAdmin : String = "Admin123"
var listaAnnunci = ArrayList<String>()
var listaUtenti = ArrayList<String>()
var idUtente = 0

//----->classi
//classe Bacheca
class Bacheca(var annuncio: String){
    var commenti = ArrayList<String>()

    fun aggiungiAnnuncio() {
        listaAnnunci.add(annuncio)
    }

    fun aggiungiCommento(){
        println("Quale annuncio vuoi commentare? Scrivine l'indice")
        var ann = readLine()!!.toInt()

        println("Scrivi qui il tuo commento")
        var comm = readLine()!!.toString()

        var post = Bacheca(listaAnnunci[ann])
        commenti.add(comm)

        println("Ecco la bacheca aggiornata")
        stampaBacheca()
    }
}

class Admin(var username:String){

    fun modificaAnnuncio(indice: Int){
        println("Scrivi qui il nuovo annuncio: ")
        val elemento= readLine()!!.toString()

        listaAnnunci[indice]= elemento //metto l'annuncio nello stesso posto del precedente

    }

    fun stampaUtenti(){
        println("Gli utenti sono: ")
        for(i in 0 until listaUtenti.size){
            if(listaUtenti[i] == " ")
                continue
            else
                println(listaUtenti[i]+" - ID utente: ${i}")
        }
    }

    fun rimuoviUtente(){
        stampaUtenti()

        println("Inserisci l'ID dell'utente che vuoi bannare ")
        var id= readLine()!!.toInt()

        listaUtenti[id] = " "   //al posto dell'utente assegnerò un campo vuoto in lista, così non si sballano gli ID degli altri utenti
    }

}


//----->main
fun main(){
    //dichiarazione variabili
    var scelta : Int
    var username : String
    var pwd : String
    var pwdC : String
    var azione : Int
    var accessoAdmin : Boolean = true
    var accessoUser : Boolean = true
    var nuovoannuncio : String
    var login = true
    var post: Bacheca
    var accessoAdmin1= true

    //aggiungo alcuni annunci alla lista di default
    post = Bacheca("Vendesi Chitarra, 50€")
    post.aggiungiAnnuncio()
    post = Bacheca("Vendesi Materasso, 10€")
    post.aggiungiAnnuncio()
    post = Bacheca("Vendesi Lavatrice, 200€")
    post.aggiungiAnnuncio()
    post = Bacheca("Vendesi Tastiera PC, 25€")
    post.aggiungiAnnuncio()
    post = Bacheca("Vendesi TV, 100€")
    post.aggiungiAnnuncio()

    do {
        //fase login
        println("Benvenuto \nPremi 1 per accedere come admin, premi 2 per accedere come utente, 3 per uscire")
        scelta = readLine()!!.toInt()

        //accesso admin
        if (scelta == 1) {
            do{
                println("Inserire username: ")
                username = readLine()!!.toString()
               //ciclo per controllo password admin
                println("Inserisci la password da admin: ")
                pwd = readLine()!!.toString()

                if (pwd == pwdAdmin) {
                    var amministratore = Admin(username)
                    do {
                        println("-----Sei un admin-----")
                        println(
                            "Scegli un'azione da eseguire. \nPremi 1 per eliminare o modificare annunci sulla bacheca." +
                                    "\n Premi 2 per vedere la lista utenti. \nPremi 3 per bannare un utente" +
                                    "\n Premi 4 per effettuare il logout"
                        )
                        azione = readLine()!!.toInt()

                        when (azione) {
                            1 -> { //l'admin vuole modificare la bacheca

                                println("Premi 1 se vuoi eliminare un annuncio, premi 2 se ne vuoi modificare uno.")
                                var opz = readLine()!!.toInt()

                                when (opz) {
                                    1 -> {   //rimuovi annuncio
                                        stampaBacheca()

                                        println("Quale annuncio vuoi rimuovere? Scrivine l'indice")
                                        var j = readLine()!!.toInt()
                                        rimuoviAnnuncio(listaAnnunci[j - 1])
                                    }

                                    2 -> { //modifica annuncio
                                        stampaBacheca()

                                        println("Quale annuncio vuoi modificare? Scrivine l'indice")
                                        var i = readLine()!!.toInt()

                                        amministratore.modificaAnnuncio(i - 1)
                                        stampaBacheca()
                                    }

                                    else -> println("Valore non valido")
                                }
                            }//fine caso 1
                            2 -> {  //l'admin vuole vedere la lista utenti
                                amministratore.stampaUtenti()
                            }

                            3 -> {   //l'admin vuole bannare qualcuno
                                amministratore.rimuoviUtente()
                            }

                            4 -> {
                                println("Grazie e arrivederci :)")
                                accessoAdmin1=false
                            }

                        }//fine when admin
                    }while(accessoAdmin1)
                } else {    //se la password non è quella dell'admin, l'utente scelte se accedere come tale o riprovare come admin
                    println("Password errata. Premi 1 per riprovare ad accedere come admin, 2 per accedere come utente")
                    azione = readLine()!!.toInt()
                    if (azione == 2)
                        accessoAdmin = false
                    else
                        println("Riprova.")
                }
            } while (accessoAdmin)

        }//fine caso admin
        //accesso utente
        else if (scelta == 2) {
            println("Inserire username: ")
            username = readLine()!!.toString()

            do { //ciclo per controllo password utente
                println("Inserisci la tua password: ")
                pwd = readLine()!!.toString()
                println("Conferma la tua password: ")
                pwdC = readLine()!!.toString()

                if (pwd == pwdC)
                    accessoUser = false
                else
                    continue
            } while (accessoUser)
            accessoUser = true

            do {
                println("-----Sei un Utente-----")
                listaUtenti.add(username)   //aggiungo l'username dell'utente alla lista, alla prima iterazione il rispettivo idUtente sarà 0
                idUtente++  //incremento l'idUtente, così il prossimo utente avrà un id diverso
                println(
                    "Scegli un'azione da eseguire. \nPremi 1 per pubblicare un annuncio in bacheca. " +
                            "\nPremi 2 per commentare un annuncio. \nPremi 3 per aggiungere qualcosa al tuo carello. " +
                            "\nPremi 4 per eseguire il logout."
                )
                azione = readLine()!!.toInt()

                when (azione) {
                    1 -> {   //aggiungo un nuovo annuncio alla bacheca
                        println("Scrivi qui il tuo annuncio: ")
                        nuovoannuncio = readLine()!!.toString()

                        var post = Bacheca(nuovoannuncio)
                        post.aggiungiAnnuncio()

                    }

                    2 -> {   //aggiungo un commento per quell'annuncio
                        stampaBacheca()
                        post.aggiungiCommento()

                    }

                    3 -> {   //l'utente vuole comprare qualcosa
                        stampaBacheca()
                        println("Quale articolo vuoi aggiungere al tuo carrello? Premi l'indice")
                        var i = readLine()!!.toInt()

                        rimuoviAnnuncio(listaAnnunci[i - 1])  //una volta comprato l'oggetto, rimuovo l'annuncio
                    }

                    4 -> {   //faccio il logout
                        println("Grazie e Arrivederci :)")
                        accessoUser=false
                    }
                }//chiusura when
            }while(accessoUser)
        }//fine caso utente
        else if(scelta == 3){
            println("Grazie e arrivederci")
            login = false
        }
        else
            println("Valore non valido!")
    }while(login)

}//chiusura main

//----->funzioni di utilità per tutti
fun stampaBacheca(){
    println("Gli annunci in bacheca sono: ")
    for(i in 0 until listaAnnunci.size) {
        println("${i}: "+listaAnnunci[i])   //stampo l'annuncio
        var post = Bacheca(listaAnnunci[i])
        //if(post.commenti.isNotEmpty()) { //se ci sono commenti relativi all'annuncio, stampo pure quelli
            println("--->Commenti relativi all'annuncio: " + post.commenti)
        //}
    }
}

fun rimuoviAnnuncio(ann : String){
    val post=Bacheca(ann)
    //prima rimuovo i commenti relativi all'annuncio
    post.commenti.removeAll(post.commenti)

    //poi rimuovo l'annuncio dalla lista degli annunci
    listaAnnunci.remove(ann)
}