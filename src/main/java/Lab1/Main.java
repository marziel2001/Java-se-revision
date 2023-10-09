package Lab1;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        //////////// TASK 2 ////////////
        System.out.println("TASK 2");

        Map<String, Brand> brands = new TreeMap<>();

        brands.put("Nikon", Brand.builder().name("Nikon").country("Japan").brandValueDollars(3450000000.00).yearOfEst(1917).models(new LinkedList<>()).build());
        brands.put("Sony", Brand.builder().name("Sony").country("Japan").brandValueDollars(10800000000.00).yearOfEst(1946).models(new LinkedList<>()).build());
        brands.put("Canon", Brand.builder().name("Canon").country("Japan").brandValueDollars(30300000000.00).yearOfEst(1937).models(new LinkedList<>()).build());
        brands.put("Pentax", Brand.builder().name("Pentax").country("Japan").brandValueDollars(200000000.00).yearOfEst(1919).models(new LinkedList<>()).build());

        // wstawianie konkretnych modeli pod brands
        brands.get("Nikon").getModels().add(Model.builder().name("D800").price(2999.95).announceYear(2012).brand(brands.get("Nikon")).build());
        brands.get("Nikon").getModels().add(Model.builder().name("D850").price(3299.95).announceYear(2017).brand(brands.get("Nikon")).build());
        brands.get("Nikon").getModels().add(Model.builder().name("Z6").price(1999.95).announceYear(2018).brand(brands.get("Nikon")).build());
        brands.get("Nikon").getModels().add(Model.builder().name("Z7").price(3399.95).announceYear(2018).brand(brands.get("Nikon")).build());
        brands.get("Nikon").getModels().add(Model.builder().name("D7500").price(1249.95).announceYear(2017).brand(brands.get("Nikon")).build());

        brands.get("Sony").getModels().add(Model.builder().name("Alpha A7 III").price(1998.00).announceYear(2018).brand(brands.get("Sony")).build());
        brands.get("Sony").getModels().add(Model.builder().name("Cyber-shot RX100 VII").price(1298.00).announceYear(2019).brand(brands.get("Sony")).build());
        brands.get("Sony").getModels().add(Model.builder().name("Alpha A6600").price(1398.00).announceYear(2019).brand(brands.get("Sony")).build());
        brands.get("Sony").getModels().add(Model.builder().name("Alpha A6400").price(898.00).announceYear(2019).brand(brands.get("Sony")).build());
        brands.get("Sony").getModels().add(Model.builder().name("Cyber-shot RX10 IV").price(1398.00).announceYear(2017).brand(brands.get("Sony")).build());

        brands.get("Canon").getModels().add(Model.builder().name("EOS 5D Mark IV").price(2499.00).announceYear(2016).brand(brands.get("Canon")).build());
        brands.get("Canon").getModels().add(Model.builder().name("EOS R6").price(2499.00).announceYear(2020).brand(brands.get("Canon")).build());
        brands.get("Canon").getModels().add(Model.builder().name("PowerShot G7 X Mark III").price(699.00).announceYear(2019).brand(brands.get("Canon")).build());
        brands.get("Canon").getModels().add(Model.builder().name("EOS Rebel T7i").price(699.00).announceYear(2017).brand(brands.get("Canon")).build());
        brands.get("Canon").getModels().add(Model.builder().name("EOS-1D X Mark III").price(6499.00).announceYear(2020).brand(brands.get("Canon")).build());

        brands.get("Pentax").getModels().add(Model.builder().name("Pentax K-1 Mark II").price(1799.95).announceYear(2018).brand(brands.get("Pentax")).build());
        brands.get("Pentax").getModels().add(Model.builder().name("Pentax KP").price(1099.95).announceYear(2017).brand(brands.get("Pentax")).build());
        brands.get("Pentax").getModels().add(Model.builder().name("Pentax K-70").price(646.95).announceYear(2016).brand(brands.get("Pentax")).build());
        brands.get("Pentax").getModels().add(Model.builder().name("Pentax K-S2").price(549.95).announceYear(2015).brand(brands.get("Pentax")).build());
        brands.get("Pentax").getModels().add(Model.builder().name("Pentax Q-S1").price(249.95).announceYear(2014).brand(brands.get("Pentax")).build());


        brands.values().forEach((x) -> {
            System.out.println(x);
            x.getModels().forEach(System.out::println);
        });

        System.out.println();
        //////////// TASK 3 ////////////
        System.out.println("TASK 3");

        Set<Model> allModels =
            brands.values()
            .stream()
            .flatMap(x->x.getModels().stream())
            .collect(Collectors.toSet());

        allModels.stream().forEach(System.out::println);

        System.out.println();
        //////////// TASK 4 ////////////
        System.out.println("TASK 4");

        allModels.stream().filter(x -> x.getName().startsWith("A") || x.getName().startsWith("C"))
            .sorted((Comparator.comparing(Model::getPrice)).reversed()).forEach(System.out::println);

        System.out.println();
        //////////// TASK 5 ////////////
        System.out.println("TASK 5");

        List<ModelDto> modeleDto = allModels.stream()
            .map(m -> new ModelDto(m.getBrand().getName(), m.getName(), m.getPrice(), m.getAnnounceYear()))
            .sorted()
            .toList();

        modeleDto.forEach(System.out::println);

        System.out.println();
        //////////// TASK 6 ////////////
        System.out.println("TASK 6");

        try {
            OutputStream os = new FileOutputStream("./object.obj");
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
            InputStream is = new FileInputStream("./object.obj");
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
        System.out.println("TASK 7");

        ForkJoinPool pool1 = new ForkJoinPool(3);

        try {
            pool1.submit(() -> {
                brands.values().parallelStream()

                    .filter(x -> x.getName().equals("Nikon") ||
                                 x.getName().equals("Sony") ||
                                 x.getName().equals("Canon") ||
                                 x.getName().equals("Pentax"))
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
