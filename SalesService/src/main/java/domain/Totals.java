package domain;

public class Totals {
    
    private Double totalPrice;
    private Double totalTax;
    private Double totalPayment;
    
    public Totals(){
    }

    public Totals(Double totalPrice, Double totalTax, Double totalPayment) {
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.totalPayment = totalPayment;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }
    
    
}
