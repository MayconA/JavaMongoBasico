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

		alunos.deleteOne(Filters.eq("nome", "Jo�o Alterado"));

		clienteMongo.close();

	}

	private static void atualizarDocumento() {
		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		alunos.updateOne(Filters.eq("nome", "Jo�o"), new Document("$set", new Document("nome", "Jo�o Alterado")));

		Document alunoAlterado = alunos.find(Filters.eq("nome", "Jo�o Alterado")).first();

		System.out.println(alunoAlterado);

		clienteMongo.close();

	}

	private static void inserirDocumento() {

		MongoClient clienteMongo = new MongoClient();

		MongoDatabase bancoDeDados = clienteMongo.getDatabase("test");

		MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");

		Document novoAluno = new Document("nome", "Jo�o").append("data_nascimento", new Date(2003, 11, 11))
				.append("curso", new Document("nome", "Hist�ria")).append("notas", Arrays.asList(10, 9.8))
				.append("habilidades", Arrays.asList(new Document().append("nome", "Ingl�s").append("nivel", "B�sico"),
						new Document().append("nome", "Espanhol").append("n�vel", "B�sico")));

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
