package menu;

public class Menu extends AbstractMenu {
    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 10;
    }

    @Override
    public String show() {
        return "\nMENU\n" +
                "1.Add model.Entry\n" +
                "2.Add Cyclic Entry\n" +
                "3.Add Category\n" +
                "4.Remove model.Entry\n" +
                "5.Remove Cyclic Entry\n" +
                "6.Modify model.Entry\n" +
                "7.Modify Cyclic Entry\n" +
                "8.Show model.Entry List\n" +
                "9.Show Cyclic Entries List\n" +
                "10.Show Category List\n" +
                "0.Exit\n" +
                "Please select " + getMinInputNumber() + "-" + getMaxInputNumber() + "!";
    }
}
