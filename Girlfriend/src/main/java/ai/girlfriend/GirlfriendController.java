package ai.girlfriend;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/girlfriend")
public class GirlfriendController implements CommandLineRunner {

    @Autowired
    ChatModel chatModel;



    @PostMapping("/message")
    public ResponseEntity<String> getGirlfriendMessage(@RequestBody GirlfriendRequest request) {

        String finalPromnt = Promnts.GRILFRIEND_PROMPT
                .replace("Use the following response to the user's request:","")
                .replace("{","")
                .replace("}","")
                .replace("response","")
                .replace(":","")
                .replace("\"","")
                .trim();

        ChatResponse response = chatModel.call( new Prompt(
                finalPromnt,
                OpenAiChatOptions.builder()
                        .withModel("gpt-4o")
                        .withTemperature(0.4)
                        .build()
        ));


        String output = String.valueOf(response.getResult());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(output);
    }

    @Override
    public void run(String... args) throws Exception {
//        GirlfriendRequest request = new GirlfriendRequest();
//        request.setMessage();
//        this.getGirlfriendMessage()
    }
}
