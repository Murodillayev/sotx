package uz.pdp.sotx.repository;

import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.entity.Ad;

import java.util.List;
import java.util.Optional;

public interface AdRepository {

    Ad save(Ad ad);

    Optional<Ad> findById(String id);

    List<Ad> findAll();
}
