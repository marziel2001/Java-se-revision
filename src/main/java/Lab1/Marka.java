package Lab1;

import lombok.*;

import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder

public class Marka implements Comparable<Marka> {
    private String nazwa;
    private Integer rokZalozenia;
    private String krajZalozenia;
    private Double wartosc;
    @ToString.Exclude
    public LinkedList<Model> modele;

    @Override
    public int compareTo(Marka o) {
        if(this.equals(o) && this.hashCode() == o.hashCode()) {
            return 0;
        }
        else {
            return this.nazwa.toLowerCase().compareTo(o.nazwa.toLowerCase());
        }
    }
}
