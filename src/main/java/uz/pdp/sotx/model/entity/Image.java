package uz.pdp.sotx.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String id;
    private String fileName; // sdjkhghjadsgjghkasdhjgkasdkghj.jpeg
    private String originalName; // javhor.jpeg
    private Long size; // 12321321 byte
    private String contentType; // img/jpeg, img/png
}
