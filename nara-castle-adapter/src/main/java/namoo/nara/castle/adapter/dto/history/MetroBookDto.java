package namoo.nara.castle.adapter.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class MetroBookDto {
    //
    private List<ParticipantMetroDto> metroDtos;

    public MetroBookDto() {
        //
    }

    public void addMetroDto(ParticipantMetroDto metroDto) {
        //
        if (metroDtos == null) {
            metroDtos = new ArrayList<>();
        }
        metroDtos.add(metroDto);
    }

    public List<ParticipantMetroDto> getMetroDtos() {
        return metroDtos;
    }

    public void setMetroDtos(List<ParticipantMetroDto> metroDtos) {
        this.metroDtos = metroDtos;
    }

}
