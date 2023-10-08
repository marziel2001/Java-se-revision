package Lab1;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        //////////// ZADANIE 2 ////////////

        Map<String, Marka> marki = new TreeMap<>();

        // tworzenie marek samochodów
        marki.put("Volkswagen", Marka.builder().nazwa("Volkswagen").krajZalozenia("Niemcy").wartosc(13716000000.00).rokZalozenia(1937).modele(new LinkedList<>()).build());
        marki.put("Toyota", Marka.builder().nazwa("Toyota").krajZalozenia("Japonia").wartosc(22086000000.00).rokZalozenia(1937).modele(new LinkedList<>()).build());
        marki.put("Ford", Marka.builder().nazwa("Ford").krajZalozenia("Stany Zjednoczone").wartosc(19496000000.00).rokZalozenia(1903).modele(new LinkedList<>()).build());
        marki.put("Honda", Marka.builder().nazwa("Honda").krajZalozenia("Japonia").wartosc(5591000000.00).rokZalozenia(1948).modele(new LinkedList<>()).build());
        marki.put("BMW", Marka.builder().nazwa("BMW").krajZalozenia("Niemcy").wartosc(5794000000.00).rokZalozenia(1916).modele(new LinkedList<>()).build());
        marki.put("Mercedes-Benz", Marka.builder().nazwa("Mercedes-Benz").krajZalozenia("Niemcy").wartosc(7101000000.00).rokZalozenia(1926).modele(new LinkedList<>()).build());
        marki.put("Audi", Marka.builder().nazwa("Audi").krajZalozenia("Niemcy").wartosc(12620000000.00).rokZalozenia(1909).modele(new LinkedList<>()).build());
        marki.put("Chevrolet", Marka.builder().nazwa("Chevrolet").krajZalozenia("Stany Zjednoczone").wartosc(5208000000.00).rokZalozenia(1911).modele(new LinkedList<>()).build());
        marki.put("Mazda", Marka.builder().nazwa("Mazda").krajZalozenia("Japonia").wartosc(7750000000.00).rokZalozenia(1920).modele(new LinkedList<>()).build());

        // wstawianie konkretnych modeli pod marki
        marki.get("Volkswagen").getModele().add(Model.builder().nazwa("Passat B5").cena(9000.00).rokWprowadzenia(1996).marka(marki.get("Volkswagen")).build());
        marki.get("Toyota").getModele().add(Model.builder().nazwa("Camry").cena(12000.00).rokWprowadzenia(1992).marka(marki.get("Toyota")).build());
        marki.get("Ford").getModele().add(Model.builder().nazwa("F-150").cena(32000.00).rokWprowadzenia(1948).marka(marki.get("Ford")).build());
        marki.get("Honda").getModele().add(Model.builder().nazwa("Civic").cena(8000.00).rokWprowadzenia(1972).marka(marki.get("Honda")).build());
        marki.get("BMW").getModele().add(Model.builder().nazwa("3 Series").cena(15000.00).rokWprowadzenia(1975).marka(marki.get("BMW")).build());
        marki.get("Mercedes-Benz").getModele().add(Model.builder().nazwa("E-Class").cena(22000.00).rokWprowadzenia(1953).marka(marki.get("Mercedes-Benz")).build());
        marki.get("Audi").getModele().add(Model.builder().nazwa("A4").cena(11000.00).rokWprowadzenia(1994).marka(marki.get("Audi")).build());
        marki.get("Chevrolet").getModele().add(Model.builder().nazwa("Silverado").cena(28000.00).rokWprowadzenia(1998).marka(marki.get("Chevrolet")).build());
        marki.get("Mazda").getModele().add(Model.builder().nazwa("CX-5").cena(15000.00).rokWprowadzenia(2012).marka(marki.get("Mazda")).build());
        marki.get("Toyota").getModele().add(Model.builder().nazwa("Corolla").cena(10000.00).rokWprowadzenia(1966).marka(marki.get("Toyota")).build());
        marki.get("Ford").getModele().add(Model.builder().nazwa("Mustang").cena(27000.00).rokWprowadzenia(1964).marka(marki.get("Ford")).build());
        marki.get("Honda").getModele().add(Model.builder().nazwa("Accord").cena(8500.00).rokWprowadzenia(1976).marka(marki.get("Honda")).build());
        marki.get("BMW").getModele().add(Model.builder().nazwa("5 Series").cena(16000.00).rokWprowadzenia(1972).marka(marki.get("BMW")).build());

        marki.values().forEach((x) -> {
            System.out.println(x);
            x.getModele().forEach(System.out::println);
        });

        System.out.println();
        //////////// ZADANIE 3 ////////////

        Set<Model> wszystkieModele =
            marki.values()
            .stream()
            .flatMap(x->x.getModele().stream())
            .collect(Collectors.toSet());

        wszystkieModele.stream().forEach(System.out::println);

        System.out.println();
        //////////// ZADANIE 4 ////////////

        wszystkieModele.stream().filter(x -> x.getNazwa().startsWith("A") || x.getNazwa().startsWith("C"))
            .sorted((Comparator.comparing(Model::getCena)).reversed()).forEach(System.out::println);

        System.out.println();
        //////////// ZADANIE 5 ////////////

        List<ModelDto> modeleDto = wszystkieModele.stream()
            .map(m -> new ModelDto(m.getMarka().getNazwa(), m.getNazwa(), m.getCena(), m.getRokWprowadzenia()))
            .sorted()
            .toList();

        modeleDto.forEach(System.out::println);

        System.out.println();
        //////////// ZADANIE 6 ////////////
        try {
            OutputStream os = new FileOutputStream("./object.txt");
            ObjectOutputStream oos = new ObjectOutputStream(os);

            for(Marka m : marki.values())
            {
                oos.writeObject(m);
            }
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Marka> markiZPliku = new TreeMap<>();

        try {
            InputStream is = new FileInputStream("./object.txt");
            ObjectInputStream ois = new ObjectInputStream(is);

            Marka tmp = null;
            while(true) {
                try {
                    tmp = (Marka)ois.readObject();
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
                markiZPliku.put(tmp.getNazwa(), tmp);
            }
            ois.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Odczytano z pliku: ");

        markiZPliku.values().forEach((x) -> {
            System.out.println(x);
            x.getModele().forEach(System.out::println);
        });
    }
}
