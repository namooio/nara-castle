package namoo.nara.castle.adapter.dto.history;

import namoo.nara.castle.domain.entity.history.MetroBook;
import namoo.nara.castle.domain.entity.history.ParticipantMetro;

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

    public MetroBook toDomain() {
        //
        MetroBook metroBook = new MetroBook();
        if (metroDtos != null) {
            for(ParticipantMetroDto metroDto : metroDtos) {
                metroBook.addMetro(metroDto.toDomain());
            }
        }
        return metroBook;
    }

    public static MetroBookDto newInstance(MetroBook metroBook) {
        //
        MetroBookDto metroBookDto = new MetroBookDto();
        List<ParticipantMetro> metroList = metroBook.findAll();
        if (metroList != null) {
            for(ParticipantMetro metro : metroList) {
                metroBookDto.addMetroDto(ParticipantMetroDto.newInstance(metro));
            }
        }
        return metroBookDto;
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
