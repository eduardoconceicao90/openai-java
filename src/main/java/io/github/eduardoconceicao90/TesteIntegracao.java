package io.github.eduardoconceicao90;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;

public class TesteIntegracao {

    public static void main(String[] args) {

        var user = "Gere 5 produtos";
        var system = "Gere produtos ficticios para um ecommerce apenas com os nomes dos produtos solicitatos pelo usuario";

        var chave = System.getenv("OPENAI_KEY");
        var service = new OpenAiService(chave);

        var chatCompletionRequest = ChatCompletionRequest
                                    .builder()
                                    .model("gpt-3.5-turbo")
                                    .messages(Arrays.asList(
                                            new ChatMessage(ChatMessageRole.USER.value(), user),
                                            new ChatMessage(ChatMessageRole.SYSTEM.value(), system)
                                    ))
                                    .build();

        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(c -> System.out.println(c.getMessage().getContent()));
    }
}
