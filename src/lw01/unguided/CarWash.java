public class CarWash extends WashService {

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int d = getDays();
        int baseCharge;
        if (d <= 3) {
            baseCharge = d * 35000;
        } else {
            baseCharge = (3 * 35000) + ((d - 3) * 25000);
        }
        return baseCharge + 15000;
    }

    @Override
    public String label() {
        return "Car";
    }
}