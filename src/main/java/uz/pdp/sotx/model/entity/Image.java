package uz.pdp.sotx.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.sotx.model.entity.base.IdEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image extends IdEntity {

    @Column(nullable = false, unique = true)
    private String fileName; // sdjkhghjadsgjghkasdhjgkasdkghj.jpeg

    @Column(nullable = false)
    private String originalName; // javhor.jpeg

    @Column(nullable = false)
    private Long size; // 12321321 byte

    @Column(nullable = false)
    private String contentType; // img/jpeg, img/png

    @Column(nullable = false)
    private String objectId;
}
