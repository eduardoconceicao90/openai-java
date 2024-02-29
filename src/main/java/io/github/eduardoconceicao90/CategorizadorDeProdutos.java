package io.github.eduardoconceicao90;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;

public class CategorizadorDeProdutos {

    public static void main(String[] args) {
        var user = "Escova de dentes";
        var system = "Você é um categorizador de produtos";

        var chave = System.getenv("OPENAI_KEY");
        var service = new OpenAiService(chave);

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
            System.out.println("------------------------");
        });
    }
}
