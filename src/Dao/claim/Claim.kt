// Alberto Rodriguez CPSC 411 HW 1

package HW1Server411.Dao.claim

import com.google.gson.Gson


import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

data class Claim(var id: UUID?, var title:String?, var date:String?, var isSolved: Int = 0)

fun main() {
    // JSON Serialization
    var newId = UUID.randomUUID()
    var pObj = Claim(id = newId, title = "Sampson", date = "6475847474", isSolved = 0)
    var jsonStr = Gson().toJson(pObj)
    println("The converted JSON string : ${jsonStr}")

    // Serialization of List<Person>
    var pList : MutableList<Claim> = mutableListOf()
    pList.add(pObj)
    newId = UUID.randomUUID()
    pList.add(Claim(id = newId, title = "Lam", date = "647383829", isSolved = 0))
    val listJsonString = Gson().toJson(pList)
    //JSONArray object
    println("${listJsonString}")

    // List<Person> object deserialization
    val personListType : Type = object : TypeToken<Claim>(){}.type

    // JSON Deserialization
    var pObj1 : Claim
    jsonStr = "{\"lastName\":\"Lam\", \"ssn\":\"647483839\"}"
    pObj1 = Gson().fromJson(jsonStr, Claim::class.java)
    println("${pObj1.toString()}")

}