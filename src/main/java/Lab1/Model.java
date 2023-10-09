package Lab1;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Model implements Comparable<Model>, Serializable {
    @EqualsAndHashCode.Exclude // to avoid circular dependencies
    private Brand brand;
    private String name;
    private Double price;
    private Integer announceYear;

    @Override
    public int compareTo(Model o) {
        if(this.equals(o) && (this.hashCode() == o.hashCode())) {
            return 0;
        }
        else {
            return this.name.toLowerCase().compareTo(o.name.toLowerCase());
        }
    }
}
