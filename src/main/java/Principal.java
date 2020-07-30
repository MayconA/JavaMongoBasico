import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal {

	public static void main(String[] args) {
		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		Document aluno = alunos.find().first();

		System.out.println(aluno);

		clienteMongo.close();

	}

}