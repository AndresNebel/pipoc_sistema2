package sistemac2;

import static java.lang.System.getenv;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("getOrders")
public class sistemaC2 {
	final static String orderTemplate =  
			"<order>\n" + 
			"  <ID>__orderID__</ID>\n" + 
			"  <name>Orden de importación</name>\n" + 
			"  <date>__timestamp__</date>\n" +
			"  <value>__value__</value>\n" +
			"  <orderset>__orderset__</orderset>\n" +
			"</order>";
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getOrders() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateString = dateFormat.format(now);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		Random randomGenerator = new Random(now.getTime());
		
		String orders = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
						"<orders>";
		
		int multiplier = Integer.parseInt(getenv("orders_amount_chance_multiplier"));
		int randomLowNumber = calendar.get(Calendar.SECOND)*multiplier; //un numero entre 0 y 300
		for (int i=0; i<randomLowNumber; i++) {
			String idGenerated = Integer.toString(randomGenerator.nextInt());
			String value = Integer.toString(randomGenerator.nextInt());
			String newOrder = orderTemplate.replace("__orderID__", idGenerated)
								.replace("__timestamp__", dateString)
								.replace("__orderset__", Integer.toString(randomLowNumber))
								.replace("__value__", value); //I am the senate!
			orders += "\n"+newOrder; 
		}
		orders += "\n</orders>";
		return orders;		
	}
	
}
