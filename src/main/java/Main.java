import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("employees.csv");
        file.createNewFile();

        Company company = new Company();
        Employee[] employees = company.getEmployees("employees.csv");
        company.saveStats(employees, "stats.txt");

    }
}
