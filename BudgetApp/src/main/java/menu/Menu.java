package menu;

public class Menu extends AbstractMenu {
    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 12;
    }

    @Override
    public String show() {
        return "\nMENU\n" +
                "1.Add Entry\t\t\t" +
                "2.Add Cyclic Entry\t\t\t" +
                "3.Add Category\n" +
                "4.Remove Entry\t\t" +
                "5.Remove Cyclic Entry\n" +
                "6.Modify Entry\t\t" +
                "7.Modify Cyclic Entry\n" +
                "8.Show Entry List\t" +
                "9.Show Cyclic Entries List\t" +
                "10.Show Category List\n" +
                "11.Switch wallet\t" +
                "12.Manage wallets\n" +
                "0.Exit\n" +
                "Please select " + getMinInputNumber() + "-" + getMaxInputNumber() + "!";
    }
}
