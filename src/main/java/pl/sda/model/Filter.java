package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private String cityName;
}
