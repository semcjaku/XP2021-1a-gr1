package menu;

public class Menu extends AbstractMenu {
    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 6;
    }

    @Override
    public String show() {
        return "\nMENU\n" +
                "1.Add model.Entry\n" +
                "2.Add Category\n" +
                "3.Remove model.Entry\n" +
                "4.Modify model.Entry\n" +
                "5.Show model.Entry List\n" +
                "6.Show Category List\n" +
                "0.Exit\n" +
                "Please select 0-6!";
    }
}
