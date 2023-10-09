package Lab1;

import lombok.*;

import java.io.Serializable;
import java.util.LinkedList;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Brand implements Comparable<Brand>, Serializable {
    private String name;
    private Integer yearOfEst;
    private String country;
    private Double brandValue;
    @ToString.Exclude
    public LinkedList<Model> models;

    @Override
    public int compareTo(Brand o) {
        if(this.equals(o) && (this.hashCode() == o.hashCode())) {
            return 0;
        }
        else {
            return this.name.toLowerCase().compareTo(o.name.toLowerCase());
        }
    }
}
