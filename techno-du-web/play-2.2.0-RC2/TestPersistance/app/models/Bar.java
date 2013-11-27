package models;
 
import java.util.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.validation.Constraints.*;
import javax.persistence.*;

import play.db.*; 

 
@Entity
public class Bar extends Model {
 
	 @Id	
    public int id;
    public String label;
    public String adresse;
    public float lat;
    public float longi;
    
	@ManyToMany
	public List<User> IsFavorit;
	@ManyToOne
	public List<Drink> Drinks;
	
	public static Finder<Long,Bar> find = new Finder(Long.class, Bar.class);
    
    public Bar(String label, String adresse, float lat, float longi) {
		this.label = label;
		this.adresse = adresse;
		this.lat = lat;
		this.longi = longi;
		this.IsFavorit = new ArrayList();
		this.Drinks = new ArrayList();
    }
    
    public static List<Bar> all() {
		return find.all();
	}
    
    public static Bar find(Long id){
    	return find.byId(id);
    }

	public static void delete(Long id) {
	find.ref(id).delete();
}
	public static void create(Bar bar){
		bar.save();
	}

	public static Bar findByLabel(String label) throws Exception {
		Bar resultat;
		
		resultat =  find.where().eq("label", label).findUnique();
		
		if (resultat.equals(null)) throw new Exception();
		
		return resultat;
	}

	public List<User> getIsFavorit() {
		return IsFavorit;
	}

	public List<Drink> getDrinks() {
		return Drinks;
	}

	public void setIsFavorit(List<User> isFavorit) {
		IsFavorit = isFavorit;
	}
}
