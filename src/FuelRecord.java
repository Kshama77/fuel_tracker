import java.time.LocalDate;

public class FuelRecord {
    private final LocalDate date;
    private final double odometer;
    private final double quantity;
    private final double cost;

    public FuelRecord(LocalDate date, double odometer, double quantity, double cost) {
        this.date = date;
        this.odometer = odometer;
        this.quantity = quantity;
        this.cost = cost;
    }

    public LocalDate getDate() { return date; }
    public double getOdometer() { return odometer; }
    public double getQuantity() { return quantity; }
    public double getCost() { return cost; }

    public double getCostPerUnit() {
        return quantity == 0 ? 0 : cost / quantity;
    }

    @Override
    public String toString() {
        return String.format(
            "Date: %s | Odometer: %.1f | Fuel: %.2f units | Cost: $%.2f | Cost/unit: $%.2f",
            date, odometer, quantity, cost, getCostPerUnit()
        );
    }
}
