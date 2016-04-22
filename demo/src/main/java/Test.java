import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import namoo.nara.castle.adapter.dto.CastleFindDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 14..
 */
public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        CastleFindDto castleFindDto = new CastleFindDto();
        castleFindDto.setName("test");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Seri)
        System.out.print(objectMapper.writeValueAsString(castleFindDto));

    }
}
