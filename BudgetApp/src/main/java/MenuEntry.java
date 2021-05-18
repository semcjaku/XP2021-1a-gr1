import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuEntry extends AbstractMenu {
    public MenuEntry() {
        this.scanner= new Scanner(System.in);
    }

    public MenuEntry(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return 5;
    }

    @Override
    public String show() {
        return "\nMENU ENTRY\n" +
                "1.Add Entry with amount\n" +
                "2.Add Entry with amount and category list\n" +
                "3.Add Entry with amount and cyclicDay\n" +
                "4.Add Entry with amount, cyclicIntervalInDays and category list\n" +
                "5.Add Entry with amount, category list and cyclicDayOfMonth\n" +
                "Please select 1-5!";
    }

    @Override
    public int read(String line) throws InvalidInputException {
        return super.read(line);
    }

    public int getAmountInputShow() {
        System.out.println("Provide amount:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    public List<String> getCategoryInputShow() {
        System.out.println("Provide category (delimeter ;):");
        String line = scanner.nextLine();
        return Arrays.asList(line.split(";"));
    }

    public int getCyclicIntervalInDaysInputShow() {
        System.out.println("Provide cyclic interval in days:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    public int getCyclicDayOfMonthInputShow() {
        System.out.println("Provide cyclic day of month:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    public Entry showInputsByChoice(int choice){
        int amount = getAmountInputShow();
        List<String> catList;
        int cyclicIntervalInDays;
        Entry entry;
        switch(choice) {
            case 2:
                catList = getCategoryInputShow();
                entry = new Entry(amount, catList);
                break;
            case 3:
                cyclicIntervalInDays = getCyclicIntervalInDaysInputShow();
                entry = new Entry(amount, cyclicIntervalInDays);
                break;
            case 4:
                catList = getCategoryInputShow();
                cyclicIntervalInDays = getCyclicIntervalInDaysInputShow();
                entry = new Entry(amount, catList, cyclicIntervalInDays);
                break;
            case 5:
                catList = getCategoryInputShow();
                int cyclicDayOfMonth = getCyclicDayOfMonthInputShow();
                entry = new Entry(amount, catList, 0, cyclicDayOfMonth);
                break;
            case 1:
            default:
                entry = new Entry(amount);
                break;
        }
        return entry;
    }
}
