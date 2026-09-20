public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int p = getPages();
        int baseCharge;
        if (p <= 10) {
            baseCharge = p * 1500;
        } else {
            baseCharge = (10 * 1500) + ((p - 10) * 1000);
        }
        return baseCharge + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
