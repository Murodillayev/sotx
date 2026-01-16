package uz.pdp.sotx.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface CrudService<CD, UD, D, ID, C> {

    D create(CD dto);

    D update(ID id, UD dto);

    List<D> getAll();

    default List<D> getAll(C criteria) {
        return Collections.emptyList();
    }

    D get(ID id);

    void delete(ID id);


}
