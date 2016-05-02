package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Sharukh on 5/1/16.
 */
@Entity
@Table(name = "model_supplies")
public class Supply extends Model {
    @Id
    @GeneratedValue
    public Long id;

    public String supply_name;
    public int supply_stock;
    public boolean supply_restock;
    public Double supply_price;

    public static Finder<Long,Supply> find = new Finder(Long.class, Supply.class);

    public static List<Supply> all()
    {
        return find.all();
    }

    public static Supply single(Long id)
    {
        return find.byId(id);
    }

}
