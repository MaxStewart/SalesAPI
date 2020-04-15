package domain;

public class Summary {
    
    
    private int numberOfSales;
    private double totalPayment;
    private String group;
    private String uri;

    public Summary(){
        
    }
    
    public Summary(int numberOfSales, double totalPayment, String group, String uri) {
        this.numberOfSales = numberOfSales;
        this.totalPayment = totalPayment;
        this.group = group;
        this.uri = uri;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    
    
}
