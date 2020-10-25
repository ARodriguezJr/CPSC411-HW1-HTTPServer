// Alberto Rodriguez CPSC 411 HW 1

package HW1Server411

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import HW1Server411.Dao.claim.Claim
import HW1Server411.Dao.claim.ClaimDao
import java.util.*

fun main(args: Array<String>): Unit {
    // Register PersonStore callback functions

    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // extension
    // @annotation
    // routing constructor takes only one parameter which is a lambda function
    // DSL - Domain Specific Language
    routing{
        this.get("/ClaimService/getAll"){
            val pList = ClaimDao().getAll()
            println("The number of claims : ${pList.size}")
            // JSON Serialization/Deserialization
            val respJsonStr = Gson().toJson(pList)
            call.respondText(respJsonStr, status= HttpStatusCode.OK, contentType= ContentType.Application.Json)
        }

        this.post("/ClaimService/add"){
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output)    // for further processing

            // JSON serialization/deserialization
            // GSON (Google library)
            var gsonString = Gson().fromJson(str, Claim::class.java)
            val claimObj = Claim(UUID.randomUUID(), gsonString.title, gsonString.date, isSolved = 0)
            val dao = ClaimDao().addClaim(claimObj)

            println("HTTP message is using POST method with /post ${contType} ${str}")
            call.respondText("The POST request was successfully processed. ",
                status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }
    }

}

        /*get("/ClaimService/add"){
            println("HTTP message is using GET method with /get ")
            val fn = call.request.queryParameters["FirstName"]
            val ln : String? = call.request.queryParameters["LastName"]
            val sn : String? = call.request.queryParameters["SSN"]
            val response = String.format("First Name %s and Last Name %s", fn, ln)
            //
            val pObj = Claim(fn, ln, sn)
            val dao = ClaimDao().addClaim(pObj)
            call.respondText(response, status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }

         */


