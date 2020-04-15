package domain;

public class Sale {
    
    private String id;
    private String saleDate;
    private Customer customer;
    private Totals totals;
    private SaleItem items;
    
    private Sale(){
    }

    public Sale(String id, String saleDate, Customer customer, Totals totals, SaleItem items) {
        this.id = id;
        this.saleDate = saleDate;
        this.customer = customer;
        this.totals = totals;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Totals getTotals() {
        return totals;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }

    public SaleItem getItems() {
        return items;
    }

    public void setItems(SaleItem items) {
        this.items = items;
    }
}
