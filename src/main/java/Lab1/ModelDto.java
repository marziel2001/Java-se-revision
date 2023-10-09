package Lab1;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ModelDto implements Comparable<ModelDto> {
    private String brandName;
    private String name;
    private Double price;
    private Integer announceDate;

    @Override
    public int compareTo(ModelDto o) {
        if(this.equals(o) && (this.hashCode() == o.hashCode())) {
            return 0;
        }
        else {
            int markaComparsionValue = this.brandName.toLowerCase().compareTo(o.brandName.toLowerCase());

            if(markaComparsionValue == 0) {
                return this.name.toLowerCase().compareTo(o.name.toLowerCase());
            }
            else return markaComparsionValue;
        }
    }
}
