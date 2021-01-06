package ar.com.quasar;

import ar.com.quasar.exceptions.MessageException;
import ar.com.quasar.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Test
    public void getMessage() throws MessageException {
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", ""};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        String message = messageService.getMessage(messages);
        String expectedMsg = "este es un mensaje secreto";
        assertEquals(message,expectedMsg);
    }

}
