package edu.eci.arsw.myrestaurant.beans.impl;

import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.model.RestaurantProduct;
import edu.eci.arsw.myrestaurant.beans.BillCalculator;
import edu.eci.arsw.myrestaurant.beans.TaxesCalculator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class BillWithTaxesCalculator implements BillCalculator {


    @Autowired
    TaxesCalculator taxescalc;

    @Override
    public int calculateBill(Order o, Map<String, RestaurantProduct> productsMap) {
        int total = 0;
        
        for (String p : o.getOrderedDishes()) {
            RestaurantProduct rp=productsMap.get(p);
            total += (o.getDishOrderedAmount(p) * (rp.getPrice() * (1 + taxescalc.getProductTaxes(rp))));
        }
        return total;

    }

}
