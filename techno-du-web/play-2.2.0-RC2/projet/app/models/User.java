package models;
 
import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import play.db.*; 
 
//import play.db.jpa.*;
 
@Entity
public class User extends Model {
 	
	 @Id		
	 public int id;
    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;

	//Relation @ManyToMany?????
	 public List<Bar> favoriteBar;

    public static Finder<Long,User> find = new Finder(Long.class, User.class);
    
    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

	public static List<User> all() {
			return find.all();
   	}

 	public static void delete(Long id) {
		find.ref(id).delete();
   }

	public static User FindByName(String name){
		List<User> maList = find.all();
		for(int i = 0; i < maList.size(); i++){
			if (maList.get(i).fullname.equals(name))
				return maList.get(i);
		}
				return null;
	}
	
}

