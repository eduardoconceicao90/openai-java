package io.github.eduardoconceicao90;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.Arrays;

public class CategorizadorDeProdutos {

    public static void main(String[] args) {
        var user = "Celular";
        var system = """
                        Você é um categorizador de produtos e deve responder apenas o nome da categoria do produto informado";
                        
                        Escolha uma categoria dentre a lista abaixo:
                        
                        1. Higiene pessoal
                        2. Eletronicos
                        3. Esportes
                        4. Outros
                        
                        #### Exemplo de uso:
                        
                        Pergunta: Bola de futebol
                        Resposta: Esportes
                        
                      """;

        var chave = System.getenv("OPENAI_KEY");
        var service = new OpenAiService(chave, Duration.ofSeconds(30));

        var chatCompletionRequest = ChatCompletionRequest
                                    .builder()
                                    .model("gpt-3.5-turbo")
                                    .messages(Arrays.asList(
                                            new ChatMessage(ChatMessageRole.USER.value(), user),
                                            new ChatMessage(ChatMessageRole.SYSTEM.value(), system)
                                    ))
                                    .n(5)
                                    .build();

        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(c -> {
            System.out.println(c.getMessage().getContent());
        });
    }
}
