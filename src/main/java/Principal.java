import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Principal {

	public static void main(String[] args) {

		recuperaEMostraoPrimeiro();

		deletarDocumento();

		inserirDocumento();

		atualizarDocumento();

	}

	private static void deletarDocumento() {

		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		alunos.deleteOne(Filters.eq("nome", "João Alterado"));

		clienteMongo.close();

	}

	private static void atualizarDocumento() {
		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		alunos.updateOne(Filters.eq("nome", "João"), new Document("$set", new Document("nome", "João Alterado")));

		Document alunoAlterado = alunos.find(Filters.eq("nome", "João Alterado")).first();

		System.out.println(alunoAlterado);

		clienteMongo.close();

	}

	private static void inserirDocumento() {

		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		Document novoAluno = new Document("nome", "João").append("data_nascimento", new Date(2003, 11, 11))
				.append("curso", new Document("nome", "História")).append("notas", Arrays.asList(10, 9.8))
				.append("habilidades", Arrays.asList(new Document().append("nome", "Inglês").append("nivel", "Básico"),
						new Document().append("nome", "Espanhol").append("nível", "Básico")));

		alunos.insertOne(novoAluno);

		System.out.println(novoAluno);

		clienteMongo.close();

	}

	private static void recuperaEMostraoPrimeiro() {

		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		Document aluno = alunos.find().first();

		System.out.println(aluno);

		clienteMongo.close();

	}

}
