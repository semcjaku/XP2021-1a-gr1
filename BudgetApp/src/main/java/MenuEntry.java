import java.io.Console;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuEntry extends AbstractMenu {

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
        return "MENU ENTRY\n" +
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

    public int getAmountInputShow(InputStream in) {
        System.out.println("Provide amount:");
        Scanner keyboard = new Scanner(in);
        String line = keyboard.next();
        return Integer.parseInt(line);
    }

    public List<String> getCategoryInputShow(InputStream in) {
        System.out.println("Provide category (delimeter ;):");
        Scanner keyboard = new Scanner(in);
        String line = keyboard.next();
        return Arrays.asList(line.split(";"));
    }

    public int getCyclicDayInputShow(InputStream in) {
        System.out.println("Provide cyclicDay:");
        Scanner keyboard = new Scanner(in);
        String line = keyboard.next();
        return Integer.parseInt(line);
    }

    public Entry showInputsByChoice(InputStream in, int choice){
        int amount = getAmountInputShow(in);
        List<String> catList = null;
        int cyclicDay;
        Entry entry;
        switch(choice) {
            case 2:
                catList = getCategoryInputShow(in);
                entry = new Entry(amount, catList);
                break;
            case 3:
                cyclicDay = getCyclicDayInputShow(in);
                entry = new Entry(amount, cyclicDay);
                break;
            case 4:
                catList = getCategoryInputShow(in);
                cyclicDay = getCyclicDayInputShow(in);
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
