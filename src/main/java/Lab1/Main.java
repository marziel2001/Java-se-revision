package Lab1;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        //////////// TASK 2 ////////////

        Map<String, Brand> brands = new TreeMap<>();

        brands.put("Volkswagen", Brand.builder().name("Volkswagen").country("Niemcy").brandValue(13716000000.00).yearOfEst(1937).models(new LinkedList<>()).build());
        brands.put("Toyota", Brand.builder().name("Toyota").country("Japonia").brandValue(22086000000.00).yearOfEst(1937).models(new LinkedList<>()).build());

        // wstawianie konkretnych modeli pod brands
        brands.get("Volkswagen").getModels().add(Model.builder().name("Passat B5").price(9000.00).announceDate(1996).brand(brands.get("Volkswagen")).build());
        brands.get("Toyota").getModels().add(Model.builder().name("Camry").price(12000.00).announceDate(1992).brand(brands.get("Toyota")).build());

        brands.values().forEach((x) -> {
            System.out.println(x);
            x.getModels().forEach(System.out::println);
        });

        System.out.println();
        //////////// TASK 3 ////////////

        Set<Model> wszystkieModele =
            brands.values()
            .stream()
            .flatMap(x->x.getModels().stream())
            .collect(Collectors.toSet());

        wszystkieModele.stream().forEach(System.out::println);

        System.out.println();
        //////////// TASK 4 ////////////

        wszystkieModele.stream().filter(x -> x.getName().startsWith("A") || x.getName().startsWith("C"))
            .sorted((Comparator.comparing(Model::getPrice)).reversed()).forEach(System.out::println);

        System.out.println();
        //////////// TASK 5 ////////////

        List<ModelDto> modeleDto = wszystkieModele.stream()
            .map(m -> new ModelDto(m.getBrand().getName(), m.getName(), m.getPrice(), m.getAnnounceDate()))
            .sorted()
            .toList();

        modeleDto.forEach(System.out::println);

        System.out.println();
        //////////// TASK 6 ////////////
        try {
            OutputStream os = new FileOutputStream("./object.txt");
            ObjectOutputStream oos = new ObjectOutputStream(os);

            for(Brand m : brands.values())
            {
                oos.writeObject(m);
            }
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Brand> markiZPliku = new TreeMap<>();

        try {
            InputStream is = new FileInputStream("./object.txt");
            ObjectInputStream ois = new ObjectInputStream(is);

            Brand tmp = null;
            while(true) {
                try {
                    tmp = (Brand)ois.readObject();
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
                markiZPliku.put(tmp.getName(), tmp);
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
            x.getModels().forEach(System.out::println);
        });

        System.out.println();
        //////////// TASK 7 ////////////

        ForkJoinPool pool1 = new ForkJoinPool(4);

        try {
            pool1.submit(() -> {
                brands.values().parallelStream()

                    .filter(x -> x.getName().equals("Mercedes-Benz") ||
                                 x.getName().equals("Toyota") ||
                                 x.getName().equals("BMW"))
                    .flatMap(x->x.getModels().stream())

                    .map(e -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        return e;
                    })
                    .forEach(System.out::println);
            }).get();
        }
        catch (ExecutionException | InterruptedException e) {
        }
        pool1.shutdown();
    }
}
