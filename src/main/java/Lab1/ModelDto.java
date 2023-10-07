package Lab1;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder

public class ModelDto {
    private String nazwaMarki;
    private String nazwa;
    private Double cena;
    private Integer rokWprowadzenia;
}
