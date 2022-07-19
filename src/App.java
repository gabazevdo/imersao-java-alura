import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e pegar dados da API do IMDB
        String base_url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(base_url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Pegar só os dados que interessam => (título, poster, classifição)
        var parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

    }
}
