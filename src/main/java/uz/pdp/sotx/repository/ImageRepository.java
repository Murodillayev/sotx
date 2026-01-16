package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sotx.model.entity.Image;

public interface ImageRepository extends JpaRepository<Image, String> {
}
