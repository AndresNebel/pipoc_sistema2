package sistemac2;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("getOrders")
public class sistemaC2 {
	final static String orderTemplate = "\n" + 
			"<order>\n" + 
			"  <ID>__orderID__</ID>\n" + 
			"  <name>Orden de importación</name>\n" + 
			"  <date>__timestamp__</date>\n" +
			"  <value>__value__</value>\n" +
			"</order>";
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getOrders() {
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		Random randomGenerator = new Random(now.getTime());
		
		String orders = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
						"<orders>";

		int randomLowNumber = calendar.get(Calendar.SECOND)*2; //un numero entre 0 y 120
		for (int i=0; i<randomLowNumber; i++) {
			String idGenerated = Integer.toString(randomGenerator.nextInt());
			String value = Integer.toString(randomGenerator.nextInt());
			String newOrder = orderTemplate.replace("__orderID__", idGenerated)
								.replace("__timestamp__", now.toString())
								.replace("__value__", value); //I am the senate!
			orders += "\n"+newOrder; 
		}
		return orders;		
	}
	
}
