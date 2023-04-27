import java.io.*;
import java.util.Scanner;

public class Company {

    public Employee[] getEmployees(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int lines = countLines(fileName);
        Employee[] employees = new Employee[lines];

        for (int i = 0; i < employees.length; i++) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            double salary = Double.parseDouble(split[4]);
            employees[i] = new Employee(split[0], split[1], split[2], split[3], salary);
        }
        return employees;
    }

    public void saveStats(Employee[] employees, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        if (employees == null) {
            writer.write("Brak informacji o pracownikach");
        } else {
            writer.write(getStats(employees));
        }
        writer.close();
    }

    private int countLines(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int counter = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            counter++;
        }
        return counter;
    }

    private double getAverageSalary(Employee[] employees) {
        double sum = 0;

        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return (sum / employees.length);
    }

    private double getMinimumSalary(Employee[] employees) {
        double minimum = Double.MAX_VALUE;

        for (Employee employee : employees) {
            if (employee.getSalary() < minimum) {
                minimum = employee.getSalary();
            }
        }
        return minimum;
    }

    private double getMaximumSalary(Employee[] employees) {
        double maximum = Double.MIN_VALUE;

        for (Employee employee : employees) {
            if (employee.getSalary() > maximum) {
                maximum = employee.getSalary();
            }
        }
        return maximum;
    }

    private int getDeparmentSize(Employee[] employees, String department) {
        int counter = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment().equals(department)) {
                counter++;
            }
        }
        return counter;
    }

    private String getStats(Employee[] employees) {
        return "Średnia wypłata: " + getAverageSalary(employees) + "\n" +
            "Minimalna wypłata: " + getMinimumSalary(employees) + "\n" +
            "Maksymalna wypłata: " + getMaximumSalary(employees) + "\n" +
            "Liczba pracowników IT: " + getDeparmentSize(employees, "IT") + "\n" +
            "Liczba pracowników Support: " + getDeparmentSize(employees, "Support") + "\n" +
            "Liczba pracowników Management: " + getDeparmentSize(employees, "Management");
    }
}
