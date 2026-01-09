package uz.pdp.sotx.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.repository.AdRepository;

import java.util.Optional;

@Repository
public class AdRepositoryImpl implements AdRepository {
    @Override
    public Ad save(Ad ad) {
        return null;
    }

    @Override
    public Optional<Ad> findById(String id) {
        return Optional.empty();
    }
}
