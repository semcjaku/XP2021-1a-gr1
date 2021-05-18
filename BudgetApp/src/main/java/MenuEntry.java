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
        return 4;
    }

    @Override
    public String show() {
        return "\nMENU ENTRY\n" +
                "1.Add Entry with amount\n" +
                "2.Add Entry with amount and category list\n" +
                "3.Add Entry with amount and cyclicDay\n" +
                "4.Add Entry with amount, cyclicDay and category list\n" +
                "Please select 1-4!";
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

    public int getCyclicDayInputShow() {
        System.out.println("Provide cyclicDay:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    public Entry showInputsByChoice(int choice){
        int amount = getAmountInputShow();
        List<String> catList;
        int cyclicDay;
        Entry entry;
        switch(choice) {
            case 2:
                catList = getCategoryInputShow();
                entry = new Entry(amount, catList);
                break;
            case 3:
                cyclicDay = getCyclicDayInputShow();
                entry = new Entry(amount, cyclicDay);
                break;
            case 4:
                catList = getCategoryInputShow();
                cyclicDay = getCyclicDayInputShow();
                entry = new Entry(amount, catList, cyclicDay);
                break;
            case 1:
            default:
                entry = new Entry(amount);
                break;
        }
        return entry;
    }
}
