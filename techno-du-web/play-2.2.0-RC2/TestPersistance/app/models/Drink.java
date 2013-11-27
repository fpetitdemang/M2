package models;
 
import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import play.db.*; 
 
 
@Entity
public class Drink extends Model {
 
	 @Id
    public int id;
    public String label;
    
    public Drink(String label) {
		this.label = label;
    }
 
}
