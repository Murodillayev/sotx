package uz.pdp.sotx.repository;

import uz.pdp.sotx.model.entity.Ad;

import java.util.Optional;

public interface AdRepository {

    Ad save(Ad ad);

    Optional<Ad> findById(String id);

}
