package models;
 
import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import play.db.*; 

 
@Entity
public class Bar extends Model {
 
	 @Id	
    public int id;
    public String label;
    
    public Bar(String label) {
		this.label = label;
    }
 
}
