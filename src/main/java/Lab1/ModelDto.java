package Lab1;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder

public class ModelDto implements Comparable<ModelDto> {
    private String nazwaMarki;
    private String nazwa;
    private Double cena;
    private Integer rokWprowadzenia;

    @Override
    public int compareTo(ModelDto o) {
        if(this.equals(o) && (this.hashCode() == o.hashCode())) {
            return 0;
        }
        else {
            int markaComparsionValue = this.nazwaMarki.toLowerCase().compareTo(o.nazwaMarki.toLowerCase());

            if(markaComparsionValue == 0) {
                return this.nazwa.toLowerCase().compareTo(o.nazwa.toLowerCase());
            }
            else return markaComparsionValue;
        }
    }
}
