package eu.tutorials.letexercise


fun main (){

    //String? co the chua null hoac mot gia tri cu the
    val name : String? = "ella"

    //In gia tri chuoi va in hoa
    name?.let {
        println(it.toUpperCase())
    }


}