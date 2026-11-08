import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
                this.id = id;
                this.name = name;
                this.department = department;
                this.salary = salary;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getDepartment() {
                return department;
        }

        public double getSalary() {
                return salary;
        }

        @Override
        public String toString() {
                return name;
        }
}

public class Demo {
        void nothing() {
                List<Integer> numbers = List.of(12, 1, 2, 3, 5, 6, 12);

                System.out.println(numbers.stream()
                                .distinct()
                                .limit(4)
                                .skip(1)
                                .count());

                System.out.println("Sum = " +
                                numbers.stream()
                                                .reduce(0, (num1, num2) -> num1 + num2));

                List<Integer> evenNumbers = numbers.stream()
                                .filter(num -> num % 2 == 0)
                                .peek(System.out::println)
                                .sorted()
                                .map(num -> num * 2)
                                .toList();

                System.out.println("Collect = " +
                                numbers.stream().collect(
                                                ArrayList::new,
                                                ArrayList::add,
                                                ArrayList::addAll));

                List<List<Integer>> result = List.of(
                                List.of(1, 2),
                                List.of(3, 224),
                                List.of(5, 6));

                System.out.println(result.stream().flatMap(lst -> lst.stream()).toList());

                System.out.println(evenNumbers);

                List<String> names = List.of("java", "spring", "boot");

                System.out.println(names.stream().sorted((s1, s2) -> s2.compareTo(s1))
                                .map(name -> name + "Hello")
                                .toList());

                System.out.println(
                                names.stream().collect(
                                                Collectors.joining(" # ", "$", "4")));

                System.out.println(
                                numbers.stream().collect(
                                                Collectors.groupingBy(
                                                                num -> num,
                                                                Collectors.mapping(
                                                                                num -> num, Collectors.toList()))));

                System.out.println(
                                numbers.stream().collect(
                                                Collectors.toMap(num -> num,
                                                                num -> num * 2,
                                                                (existing, replacement) -> existing - replacement)));

                System.out.println(
                                numbers.stream().collect(Collectors.summarizingDouble(num -> num)));

                System.out.println("=================================");

                System.out.println("My = " + Thread.currentThread().getName());

                numbers.parallelStream()
                                .forEach(num -> {
                                        System.out.println("Internal = " + Thread.currentThread().getName());
                                        System.out.println("Parallel stream = " + num);
                                });

                Stream<String> stream = Stream.generate(() -> "Hello world");

                stream.limit(10).forEach(System.out::println);

                Stream<Integer> streamNumbers = Stream.iterate(0, x -> x + 20);

                streamNumbers.limit(10).forEach(System.out::println);
        }

        static void problem1() {

        }

        public static void main(String[] args) {
                List<Integer> numbers = List.of(10, 50, 30, 80, 20, 80, 50);
                // System.out.println(
                // numbers.stream().collect(Collectors.minBy((num1, num2) -> num1 - num2))
                // );

                // System.out.println(numbers.stream().reduce(0, (acc, cur) -> acc + cur));

                // System.out.println(
                // numbers.stream().mapToInt(x->x).min()
                // );

                // System.out.println(
                // numbers.stream()
                // .max((num1, num2) -> num1 - num2)
                // );

                // System.out.println(
                // numbers.stream().collect(
                // Collectors.groupingBy(
                // num -> num,
                // Collectors.counting()
                // )
                // ));

                // System.out.println(
                // numbers.stream().count()
                // );

                // System.out.println(
                // numbers.stream()
                // .distinct()
                // .sorted((num1, num2) -> num2 - num1)
                // .skip(1)
                // .findFirst().orElseThrow(
                // () -> new IllegalArgumentException("")
                // )
                // );

                System.out.println(
                                numbers.stream().collect(Collectors.groupingBy(
                                                Function.identity(),
                                                Collectors.counting()))
                                                .entrySet()
                                                .stream()
                                                .filter(entry -> entry.getValue() > 1)
                                                .collect(
                                                                Collectors.toMap(
                                                                                entry -> entry.getKey(),
                                                                                entry -> entry.getValue(),
                                                                                (existing, replacement) -> existing)));

                System.out.println(

                                numbers.stream()
                                                .collect(
                                                                Collectors.groupingBy(
                                                                                Function.identity(),
                                                                                Collectors.counting()))
                                                .entrySet()
                                                .stream()
                                                .filter(
                                                                entry -> entry.getValue() == 1)
                                                .collect(
                                                                Collectors.toMap(
                                                                                entry -> entry.getKey(),

                                                                                entry -> entry.getValue(),
                                                                                (existing, replacement) -> existing)));

                System.out.println(Collections.frequency(numbers, 80));

                List<String> names = List.of("abcdbcd", "abcdabc");

                names.stream()
                                .collect(
                                                Collectors
                                                                .toMap(
                                                                                Function.identity(),
                                                                                name -> name.chars()
                                                                                                .mapToObj(c -> (char) c)
                                                                                                .collect(
                                                                                                                Collectors.groupingBy(
                                                                                                                                Function.identity(),
                                                                                                                                LinkedHashMap::new,
                                                                                                                                Collectors.counting()))
                                                                                                .entrySet()
                                                                                                .stream()
                                                                                                .filter(entry -> entry
                                                                                                                .getValue() > 1)
                                                                                                .map(entry -> entry
                                                                                                                .getKey())
                                                                                                .findFirst(),
                                                                                (existing, replacement) -> replacement))
                                .forEach((name, character) -> System.out
                                                .println(name + " -> " + character.orElse(null)));
                ;

                String word = "hello world";

                System.out.println(word.chars()
                                .mapToObj(c -> (char) c)
                                .collect(
                                                Collectors.groupingBy(Function.identity(),
                                                                LinkedHashMap::new,
                                                                Collectors.counting())));

                List<Integer> numbers1 = Arrays.asList(1, 2, 3, 3, 5);

                List<Integer> numbers2 = Arrays.asList(2, 4, 12, 51, 23, 1);

                Set<Integer> setNumbers1 = new HashSet(numbers1);

                System.out.println(
                                numbers2.stream()
                                                .filter(setNumbers1::contains)
                                                .distinct()
                                                .toList());

                Stream.concat(
                                numbers1.stream(),
                                numbers2.stream())
                                .distinct()
                                .collect(Collectors.toList());

                List<List<Integer>> listOfLists = List.of(numbers, numbers1, numbers2);

                System.out.println(listOfLists.stream().flatMap(list -> list.stream()).collect(Collectors.toList()));

                List<Employee> employees = List.of(
                                new Employee(7, "Sneha", "Finance", 70000),
                                new Employee(1, "Gopi", "IT", 80000),
                                new Employee(2, "Rahul", "HR", 50000),
                                new Employee(3, "Priya", "IT", 90000),
                                new Employee(4, "Kiran", "Finance", 60000),
                                new Employee(5, "Anil", "HR", 55000),
                                new Employee(6, "Sneha", "Finance", 70000));

                Map<String, List<Employee>> res = employees
                                .stream()
                                .collect(Collectors.groupingBy(
                                                employee -> employee.getDepartment()));

                res.forEach((department, employeesList) -> {
                        System.out.println(department + " " + employees);
                });

                System.out.println(
                                employees.stream().max(
                                                (emp1, emp2) -> Double.compare(emp1.getSalary(), emp2.getSalary())));

                System.out.println(
                                employees.stream()
                                                .sorted(
                                                                (emp1, emp2) -> Double.compare(emp2.getSalary(),
                                                                                emp1.getSalary()))
                                                .skip(2)
                                                .findFirst());

                employees.stream()
                                .collect(Collectors.groupingBy(

                                                // 1. Group employees by department
                                                Employee::getDepartment,

                                                // 2. Extract salary from each employee
                                                Collectors.mapping(
                                                                Employee::getSalary,

                                                                // 3. Collect salaries into a List
                                                                Collectors.collectingAndThen(
                                                                                Collectors.toList(),

                                                                                // 4. Find second-highest salary
                                                                                salaries -> salaries.stream()
                                                                                                .distinct()
                                                                                                .sorted(Comparator
                                                                                                                .reverseOrder())
                                                                                                .skip(1)
                                                                                                .findFirst()))));

                System.out.println(
                                employees.stream()
                                                .collect(
                                                                Collectors.groupingBy(
                                                                                emp -> emp.getDepartment(),
                                                                                Collectors.averagingDouble(
                                                                                                emp -> emp.getSalary()))));

                System.out.println(

                                employees.stream()
                                                .collect(
                                                                Collectors.partitioningBy(
                                                                                emp -> emp.getSalary() >= 70000)));

                System.out.println(
                                employees.stream()
                                                .collect(
                                                                Collectors.groupingBy(
                                                                                emp -> emp.getName(),
                                                                                Collectors.counting()))
                                                .entrySet()
                                                .stream()
                                                .filter(entry -> {
                                                        return entry.getValue() > 1;
                                                })
                                                .toList()

                );

                Map<Integer, Employee> mapIds = employees.stream()
                                .collect(
                                                Collectors.toMap(
                                                                emp -> emp.getId(),
                                                                Function.identity(),
                                                                (existing, replacement) -> existing,
                                                                LinkedHashMap::new));

                System.out.println(mapIds);

                System.out.println(
                                employees.stream()
                                                .collect(
                                                                Collectors.groupingBy(
                                                                                emp -> emp.getDepartment(),
                                                                                Collectors.averagingDouble(
                                                                                                emp -> emp.getSalary())))
                                                .entrySet()
                                                .stream()
                                                .max((entry1, entry2) -> Double.compare(entry1.getValue(),
                                                                entry2.getValue()))

                );

                System.out.println(
                                employees.stream()
                                                .sorted((emp1, emp2) -> {
                                                        if (Double.compare(emp1.getSalary(), emp2.getSalary()) == 0) {
                                                                return emp1.getName().compareTo(emp2.getName());
                                                        }

                                                        return Double.compare(emp1.getSalary(), emp2.getSalary());
                                                })
                                                .toList());

                List<String> sentences = List.of(
                                "java spring boot",
                                "java microservices spring",
                                "java spring kafka",
                                "spring boot microservices",
                                "java kafka");

                System.out.println(
                                sentences.stream()
                                                .flatMap(sentence -> Arrays.stream(sentence.split("\\s+")))
                                                .collect(
                                                                Collectors.groupingBy(
                                                                                Function.identity(),
                                                                                Collectors.counting()))
                                                .entrySet()
                                                .stream()
                                                .sorted(
                                                                Map.Entry.<String, Long>comparingByValue()
                                                                                .reversed()
                                                                                .thenComparing(Map.Entry
                                                                                                .comparingByKey()))
                                                .limit(3)
                                                .toList());

                System.out.println(
                                sentences.stream()
                                                .collect(
                                                                Collectors.groupingBy(
                                                                                sentence -> sentence.length()))
                                                .entrySet()
                                                .stream()
                                                .max((entry1, entry2) -> Long.compare(entry1.getKey(),
                                                                entry2.getKey())));
                ;

                System.out.println(
                                sentences.stream()
                                                .max((sentence1, sentence2) -> Integer.compare(sentence1.length(),
                                                                sentence2.length()))

                );

                employees.stream()
                                .collect(
                                                Collectors.groupingBy(
                                                                emp -> emp.getDepartment(),
                                                                Collectors.groupingBy(
                                                                                emp -> emp.getSalary() >= 1800 ? "High"
                                                                                                : "Low")))
                                .forEach((key, val) -> System.out.println(key + " - " + val));

                employees.stream()
                                .collect(
                                                Collectors.groupingBy(
                                                                emp -> emp.getDepartment(),
                                                                Collectors.groupingBy(
                                                                                emp -> emp.getSalary() >= 1800 ? "High"
                                                                                                : "Low",
                                                                                Collectors.mapping(emp -> emp.getName(),
                                                                                                Collectors.toList()))))
                                .forEach((key, val) -> System.out.println(key + " $ " + val));

                Map<Integer, Employee> employeeMap = employees.stream()
                                .collect(Collectors.toMap(
                                                Employee::getId,
                                                Function.identity(),
                                                (existing, replacement) -> existing.getSalary() > replacement
                                                                .getSalary()
                                                                                ? existing
                                                                                : replacement));

                int a = 0;
                int b = 1;

                List<Long> fib = Stream
                                .iterate(
                                                new long[] { 0, 1 },
                                                pair -> new long[] { pair[1], pair[0] + pair[1] })
                                .map(pair -> pair[0])
                                .limit(20)
                                .toList();

                System.out.println(fib);

        }
}