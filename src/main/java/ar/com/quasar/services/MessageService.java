package ar.com.quasar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    public String getMessage(List<List<String>> listaDeMensajes) {

        String[] mensajeOriginal = new String[listaDeMensajes.get(0).size()];

        for(int i = 0; i < listaDeMensajes.size(); i++ ) {
            for(int j = 0; j < listaDeMensajes.get(i).size(); j ++) {
                if(!listaDeMensajes.get(i).get(j).equals("")){
                    mensajeOriginal[j] = listaDeMensajes.get(i).get(j);
                }
            }
        }
        String mensajeString = String.join(" ", mensajeOriginal);
        return mensajeString;
    }

}
