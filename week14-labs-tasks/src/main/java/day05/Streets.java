package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Streets {
    private Map<String, List<Integer>> streets = new LinkedHashMap<>();


    public void readFromFile(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" ");
                if (!setUpStreet(temp[0], Integer.parseInt(temp[1]))) {
                    addNewNumber(temp[0], Integer.parseInt(temp[1]));
                }
            }
            //System.out.println(streets);
        } catch (IOException e) {
            throw new IllegalStateException("File not exist");
        }

    }

    private void addNewNumber(String street, int number) {
        int maxNumber = streets.get(street).stream()
                .mapToInt(i -> i)
                .filter(i -> i % 2 == number)
                .max()
                .orElse(-number);
        streets.get(street).add(maxNumber + 2);
    }

    private boolean setUpStreet(String street, int number) {
        if (!streets.containsKey(street)) {
            streets.put(street, new ArrayList<>());
            if (number == 0) {
                streets.get(street).add(2);
            } else {
                streets.get(street).add(1);
            }
            return true;
        }
        return false;
    }


    public void streetMap(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                streets.compute(parts[0], (k, v) -> v != null ? v : new ArrayList<>()).add(Integer.parseInt(parts[1]));
            }
            transferMap();
            // System.out.println(streets);

        } catch (IOException e) {
            throw new IllegalArgumentException("No such file");
        }
    }

    public long getNumberOfEvenByStreet(String street) {
        return streets.get(street).stream()
                .mapToInt(i -> i)
                .filter(i -> i % 2 == 0)
                .count();
    }

    private void transferMap() {

        for (Map.Entry<String, List<Integer>> entry : streets.entrySet()) {
            int evenNumber = 0;
            int oddNumber = -1;
            List<Integer> numbers = new ArrayList<>();
            transferValues(entry, evenNumber, oddNumber, numbers);
            streets.put(entry.getKey(), new ArrayList<>(numbers));
        }
    }

    private void transferValues(Map.Entry<String, List<Integer>> entry, int evenNumber, int oddNumber, List<Integer> numbers) {
        for (Integer actual : entry.getValue()) {
            if (actual == 0) {
                evenNumber += 2;
                numbers.add(evenNumber);
            } else {
                oddNumber += 2;
                numbers.add(oddNumber);
            }
        }
    }


    public static void main(String[] args) {
        Streets streets = new Streets();
        streets.streetMap(Path.of("src/main/resources/streets.txt"));
        //streets.readFromFile(Path.of("src/main/resources/streets.txt"));

        streets.streets.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        System.out.println(streets.getNumberOfEvenByStreet("Kossuth"));
        System.out.println(streets.getNumberOfEvenByStreet("Petofi"));
    }

}
