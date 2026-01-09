package uz.pdp.sotx.service;

import java.util.List;

public interface CrudService<CD, UD, D, ID> {

    void create(CD dto);

    void update(ID id, UD dto);

    List<D> getAll();

    D get(ID id);

    void delete(ID id);


}
