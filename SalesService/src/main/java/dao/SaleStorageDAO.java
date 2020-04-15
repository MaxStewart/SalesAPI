package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import domain.Sale;
import domain.*;

public class SaleStorageDAO {

    private static final Map<String, Sale> items = new TreeMap<>();

    static {
        if (items.isEmpty()) {
            // Add new sale
            Customer customer = new Customer("111", "newUser@email.com", "Plebs");
            SaleItem saleItem = new SaleItem("12333", 3.0, 10.0);
            Totals totals = new Totals(30.0, 12.0, 42.0);
            items.put("12345", new Sale("12345", "12-04-2020", customer, totals, saleItem));
            
            Customer customer1 = new Customer("123", "oldUser@email.com", "Group2");
            SaleItem saleItem1 = new SaleItem("12334", 5.0, 1.0);
            Totals totals1 = new Totals(5.0, 1.0, 6.0);
            items.put("34857348573", new Sale("34857348573", "12-04-2020", customer1, totals1, saleItem1));
            
            Customer customer2 = new Customer("111", "newUser@email.com", "Group1");
            SaleItem saleItem2 = new SaleItem("12330", 40.0, 2.0);
            Totals totals2 = new Totals(80.0, 5.0, 85.0);
            items.put("123445", new Sale("123445", "12-04-2020", customer2, totals2, saleItem2));
        }
    }

    public List<Sale> getSales() {
        return new ArrayList<>(items.values());
    }

    public void addSales(Sale sale) {
        items.put(sale.getId(), sale);
    }

    public Sale getById(String id) {
        return items.get(id);
    }

    public Sale getByCustomerId(String id) {
        for (Sale sale : items.values()) {
            if (sale.getCustomer().getId() == null ? id == null : sale.getCustomer().getId().equals(id)) {
                return sale;
            }
        }
        return null;
    }

    public List<Sale> getAllByCustomerId(String id) {
        List<Sale> sales = new ArrayList<Sale>();

        for (Sale sale : items.values()) {
            if (sale.getCustomer().getId() == null ? id == null : sale.getCustomer().getId().equals(id)) {
                sales.add(sale);
            }
        }
        return sales;
    }

    public void delete(String id) {
        items.remove(id);
    }

    public void updateItem(String id, Sale updatedSale) {
        items.put(id, updatedSale);
    }

    public boolean customerExists(String id) {

        List<Sale> sales = getSales();

        int numberOfSales = sales.size();

        for (int i = 0; i < numberOfSales; i++) {
            if (sales.get(i).getCustomer().getId() == null ? id == null : sales.get(i).getCustomer().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(String id) {
        return items.containsKey(id);
    }
    
    public Double sumCustomerSales(List<Sale> sales){
        Double total = 0.0;
        
        for (int i = 0; i < sales.size(); i++) {
            total += sales.get(i).getTotals().getTotalPayment();
        }
        return total;
    }
    
    public Summary getSummary(String id){
        List<Sale> salesFromCustomer = getAllByCustomerId(id);
        
        Summary summary = new Summary();
        summary.setNumberOfSales(salesFromCustomer.size());
        Double total = sumCustomerSales(salesFromCustomer);
        summary.setTotalPayment(total);
        
        String group;
        if(total >= 5000){
            group = "Premium";
        } else {
            group = "Plebs";
        }
        summary.setGroup(group);
        
        return summary;
    }
}
