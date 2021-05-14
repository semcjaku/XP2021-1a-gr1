public class Menu extends AbstractMenu {
    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 4;
    }

    @Override
    public String show() {
        return "\nMENU\n" +
                "1.Add Entry\n" +
                "2.Add Category\n" +
                "3.Show Entry List\n" +
                "4.Show Category List\n" +
                "0.Exit\n" +
                "Please select 0-4!";
    }
}
