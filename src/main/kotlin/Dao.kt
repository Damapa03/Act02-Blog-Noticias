
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.github.cdimascio.dotenv.dotenv

object Dao {
    private val mongoClient: MongoClient by lazy {
//        val dotenv = dotenv()
//        val connectString = dotenv["URL_MONGODB"]

        MongoClients.create("mongodb+srv://dmarin:joIfZQIqNtO99ERy@adacluster.amhxu.mongodb.net/?retryWrites=true&w=majority&appName=ADACluster")
    }

    fun getDatabase(bd: String) : MongoDatabase {
        return mongoClient.getDatabase(bd)
    }

    fun close() {
        mongoClient.close()
    }
}