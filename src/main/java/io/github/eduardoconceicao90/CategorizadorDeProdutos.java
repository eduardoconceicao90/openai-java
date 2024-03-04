package io.github.eduardoconceicao90;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;

public class CategorizadorDeProdutos {

    public static void main(String[] args) {

        var leitor = new Scanner(System.in);

        System.out.println("Digite as categorias válidas:");
        var categorias = leitor.nextLine();

        System.out.println("Digite o nome do produto:");
        var user = leitor.nextLine();

        System.out.println("----------RESPOSTA----------");

        var system = """
                        
                        Você é um categorizador de produtos e deve responder apenas o nome da categoria do produto informado";
                        
                        Escolha uma categoria dentre a lista abaixo:
                        
                        %s
                        
                        #### Exemplo de uso:
                        
                        Pergunta: Bola de futebol
                        Resposta: Esportes
                        
                        #### Regras a serem seguidas:
                        
                        Caso o usuario pergunte algo que não seja sobre categorizacao de produtos,
                        você deve responder que não pode ajudar pois seu papel é apenas responder a categoria dos produtos.
                        
                      """.formatted(categorias);

        dispararRequisicao(user, system);

    }

    public static void dispararRequisicao(String user, String system){
        var chave = System.getenv("OPENAI_KEY");
        var service = new OpenAiService(chave, Duration.ofSeconds(30));

        var chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(
                        new ChatMessage(ChatMessageRole.USER.value(), user),
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), system)
                ))
                .build();

        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(c ->  System.out.println(c.getMessage().getContent()));
    }
}
