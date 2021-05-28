package menu;

public class Menu extends AbstractMenu {
    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 5;
    }

    @Override
    public String show() {
        return "\nMENU\n" +
                "1.Add model.Entry\n" +
                "2.Add Cyclic Entry\n" +
                "3.Add Category\n" +
                "4.Show model.Entry List\n" +
                "5.Show Category List\n" +
                "0.Exit\n" +
                "Please select 0-5!";
    }
}
