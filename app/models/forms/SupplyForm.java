package models.forms;

import models.Supply;
import play.data.validation.Constraints;

/**
 * Created by Sharukh on 5/1/16.
 */
public class SupplyForm {
    @Constraints.Required
    public String supply_name;
    @Constraints.Required
    public int supply_quantity;
    @Constraints.Required
    public boolean supply_restock;
    public Double supply_price;

    public Supply formToSupply(SupplyForm supplyForm)
    {
        Supply supply = new Supply();

        supply.supply_name = supplyForm.supply_name;
        supply.supply_stock = supplyForm.supply_quantity;
        supply.supply_restock = supplyForm.supply_restock;
        supply.supply_price = supplyForm.supply_price;

        return supply;
    }
}
