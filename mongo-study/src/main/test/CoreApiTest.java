import com.database.study.core.MongoDao;
import com.database.study.core.MongoDaoImpl;
import com.database.study.core.MongoHelper;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * core包工具方法测试
 */
public class CoreApiTest {

    private MongoDao mongoDao=new MongoDaoImpl();

    @Test
    public void queryAll() throws Exception {


        MongoDatabase mongoDataBase = MongoHelper.getMongoDataBase();
        List<Map<String, Object>> c1 = mongoDao.queryAll(mongoDataBase, "c1");

        System.out.println(c1.toString());


    }





    @Test
    public void testInsert() throws Exception {
        MongoDatabase mongoDataBase = MongoHelper.getMongoDataBase();
        Document d1 = new Document();
        d1.append("name", "水浒传").append("author", "罗贯中");
        mongoDao.insert(mongoDataBase,"c1",d1);


    }

    @Test
    public void testA(){

        MongoDatabase mongoDataBase = MongoHelper.getMongoDataBase();

        MongoCollection<org.bson.Document> cb = mongoDataBase.getCollection("c1");

        FindIterable<Document> documents = cb.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


}
