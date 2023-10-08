package Lab1;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder

public class Model implements Comparable<Model> {
    @EqualsAndHashCode.Exclude // to avoid circular dependencies
    private Marka marka;
    private String nazwa;
    private Double cena;
    private Integer rokWprowadzenia;

    @Override
    public int compareTo(Model o) {
        if(this.equals(o) && (this.hashCode() == o.hashCode())) {
            return 0;
        }
        else {
            return this.nazwa.toLowerCase().compareTo(o.nazwa.toLowerCase());
        }
    }
}
