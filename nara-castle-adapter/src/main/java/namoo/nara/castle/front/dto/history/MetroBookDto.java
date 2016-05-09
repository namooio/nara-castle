package namoo.nara.castle.front.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class MetroBookDto {
    //
    private List<ParticipantMetroDto> metros = new ArrayList<>();

    public MetroBookDto() {
        //
    }

    public void addMetroDto(ParticipantMetroDto metroDto) {
        //
        if (metros == null) {
            metros = new ArrayList<>();
        }
        metros.add(metroDto);
    }

    public List<ParticipantMetroDto> getMetros() {
        return metros;
    }

    public void setMetros(List<ParticipantMetroDto> metros) {
        this.metros = metros;
    }

}
