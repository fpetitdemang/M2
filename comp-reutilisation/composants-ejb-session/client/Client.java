package converter;

import javax.naming.InitialContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import java.util.*;

public class Client

{ 
    //javac -d . Client.java 
    //export CLASSPATH=jndi.properties:.:~/glassfishv3/glassfish/lib/appserv-rt.jar 
    //java converter.Client -cp jndi.properties

    
    public static void main (String[] args){

	try {
	    InitialContext context = new InitialContext();
	    IConverter converter = (IConverter) context.lookup("java:global/converter/Converter-ejb/ConverterBean");
	    System.out.print("Entrer un montant à convertir : ");
	    Scanner sc = new Scanner(System.in);
	    double amount = sc.nextDouble();
	    amount = converter.euroToOtherCurrency(amount,"USD");
	    System.out.println("Résultat : "+amount);
        } catch (Exception ex) {
	    ex.printStackTrace();
            //Logger.getLogger(ConverterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	

	System.out.println("Ok");
    }

}
