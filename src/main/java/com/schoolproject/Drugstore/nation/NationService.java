package com.schoolproject.Drugstore.nation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.use.ProductUseForDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NationService {
    private final NationRepository nationRepository;
    private final NationMapper nationMapper;

    public Collection<NationDto> getAll() {
        List<NationDto> list = nationRepository.findAll().stream()
                .map(nationMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public NationDto getById(int id) {
        Nation nation = nationRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
        return nationMapper.toDto(nation);
    }

    public NationDto create(NationDto nationDto) {
        try {
            nationDto.setId(0);
            Nation entity = nationMapper.toEntity(nationDto);
            Nation nation = nationRepository.save(entity);
            return nationMapper.toDto(nation);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public NationDto edit(NationDto nationDto) {
        Integer id = nationDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        Nation nation = nationRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        try {
            nation = nationMapper.toEntity(nationDto);
            nationRepository.save(nation);
            return nationMapper.toDto(nation);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public NationDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        Nation nation = nationRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            nationRepository.delete(nation);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return nationMapper.toDto(nation);
    }

    public Collection<NationDto> deleteAll() throws Exception {
        Collection<NationDto> list = getAll();

        try {
            nationRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<NationDto> getByName(String name) {
        List<NationDto> list = nationRepository.findByName(name).stream()
                .map(nationMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }
}
