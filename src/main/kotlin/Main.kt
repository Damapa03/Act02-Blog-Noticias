fun main(){
    var num = 0
    println("1. Login\n 2. Register\n 3. Salir")
    while (true){
        try{ num = readLine()!!.toInt() }catch (e:Exception){println("Introduzaca un numero")}
        if (num == 1){

        }
        if (num == 2){

        }
        if (num == 3){
            break
        }
        if (num < 1 || num > 3){
            println("Ingrese una opcion valido")
        }
    }
}

fun login(){

}

fun register(){

}