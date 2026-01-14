package uz.pdp.sotx.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.sotx.model.entity.Image;

import java.lang.ScopedValue;
import java.util.Optional;

public interface ImageRepository {
    Optional<Image> findById(String id);
}
