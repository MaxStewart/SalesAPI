
import api.*;
import domain.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SalesRetrofitClient {
    
    public static void main(String[] args) throws IOException{
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        DirectoryApi directoryApi = retrofit.create(DirectoryApi.class);
        
        // POST new customer
        Customer customer = new Customer();
        customer.setId("123");
        customer.setEmail("newUser@email.com");
        customer.setGroup("Plebs");
        SaleItem saleItem = new SaleItem();
        saleItem.setProductId("1234455");
        saleItem.setQuantity(new BigDecimal(12.0));
        saleItem.setPrice(new BigDecimal(2.0));
        Totals totals = new Totals();
        totals.setTotalPrice(new BigDecimal(24.0));
        totals.setTotalTax(new BigDecimal(1.0));
        totals.setTotalPayment(new BigDecimal(25.0));
        Sale sale = new Sale();
        sale.setId("4456");
        sale.setSaleDate("1-1-2020");
        sale.setSaleItem(saleItem);
        sale.setCustomer(customer);
        sale.setTotals(totals);
        directoryApi.createNewSale(sale).execute().body();
        
        // POST another user
        Customer customer2 = new Customer();
        customer2.setId("444");
        customer2.setEmail("newUser@email.com");
        customer2.setGroup("Plebs");
        SaleItem saleItem2 = new SaleItem();
        saleItem2.setProductId("12");
        saleItem2.setQuantity(new BigDecimal(12.0));
        saleItem2.setPrice(new BigDecimal(5.0));
        Totals totals2 = new Totals();
        totals2.setTotalPrice(new BigDecimal(60.0));
        totals2.setTotalTax(new BigDecimal(3.20));
        totals2.setTotalPayment(new BigDecimal(63.20));
        Sale sale2 = new Sale();
        sale2.setId("111");
        sale2.setSaleDate("1-1-2020");
        sale2.setSaleItem(saleItem2);
        sale2.setCustomer(customer2);
        sale2.setTotals(totals2);
        directoryApi.createNewSale(sale2).execute().body();
        
        // DELETE sale
        SaleApi saleApi = retrofit.create(SaleApi.class);
        saleApi.deleteSale("111").execute().body();
        
        // GET all sales from a customer ID
        List<Sale> sales = saleApi.directorySaleCustomerIdGet("123").execute().body();
        System.out.println(sales);
        
        // GET the summary from a customer
        SummaryApi summaryApi = retrofit.create(SummaryApi.class);
        Summary summary = summaryApi.directorySaleCustomerIdSummaryGet("123").execute().body();
        System.out.println(summary);        
    }
    
}
