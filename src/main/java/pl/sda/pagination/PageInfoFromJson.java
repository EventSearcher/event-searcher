package pl.sda.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfoFromJson {

    public Page page;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Page{

        public int size;
        public int totalElements;
        public int totalPages;

    }
}
