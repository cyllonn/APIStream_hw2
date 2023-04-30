import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> soliders = persons.stream()
                .filter(person -> (person.getAge() > 17 && person.getAge() < 28))
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(person -> person.getFamily().toString())
                .collect(Collectors.toList());
        System.out.println(soliders);

        List<String> workers = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getAge() > 17))
                .filter(person -> (person.getAge() < 61 && person.getSex().equals(Sex.WOMAN) ||
                        (person.getAge() < 66 && person.getSex().equals(Sex.MAN))))
                .map(person -> person.getFamily().toString())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        System.out.println(workers);


    }
}
