package uz.pdp.sotx.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class PageableDto<T> {
    private T data;
    private int totalPages;
    private Long totalElements;

    public PageableDto(T data, int totalPages, Long totalElements) {
        this.data = data;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
