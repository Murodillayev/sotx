package uz.pdp.sotx.service;

import org.springframework.data.domain.Page;
import uz.pdp.sotx.model.dto.PageableDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface CrudService<CD, UD, D, ID, C> {

    D create(CD dto);

    D update(ID id, UD dto);

    List<D> getAll();

    default PageableDto<List<D>> getAll(C criteria) {
        return null;
    }

    D get(ID id);

    void delete(ID id);


}
