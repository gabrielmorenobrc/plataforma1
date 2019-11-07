package plataforma1.services;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Exception utilitaria que almacena feedback de validación.</p>
 * <p>Se recomienda su utilización en el código servidor para lanzamiento de errores de valaidación y sucaptura en el código cliente para provisión de feedback de valdiación.</p>
 */
public class ValidationException extends RuntimeException {

    private final List<String> messageList = new ArrayList<String>();

    public ValidationException() {
    }

    public ValidationException(String message) {
        this.addMessage(message);
    }

    /**
     * Agrega un mensaje a la lista de mensajes almacenada.
     *
     * @param message
     */
    public void addMessage(String message) {
        messageList.add(message);
    }

    /**
     * @return Provee una representación en una única cadena de la lista de mensajes almacenados
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        for (String message : messageList) {
            sb.append(message + "\r\n");
        }
        return sb.toString();
    }

    /**
     * @return Provee la lista de mensajes almacenados
     */
    public List<String> getMessageList() {
        return messageList;
    }
}
