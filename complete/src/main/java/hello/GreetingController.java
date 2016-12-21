package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@RestController
public class GreetingController {

    private static final String template = "Events: %s";
    private final AtomicLong counter = new AtomicLong();
    


   @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam("dates") String[] dates, @RequestParam("locale") String locale) {


				String[] res = new String[dates.length];
				
		        FileInputStream fileInputStream;
	
		        Properties prop = new Properties();

		 		 try {

			            fileInputStream = new FileInputStream(locale+"_dates.properties");
			            prop.load(fileInputStream);

		            for (int i = 0; i<dates.length; i++ ) {
		            	String sob = prop.getProperty(dates[i]);
		            	res[i] = dates[i]+" - "+sob;
		 		}
		    } catch (IOException e) {
		            return new Greeting(counter.incrementAndGet(),
                            String.format(template,"Ошибка в программе: файла не обнаружено"));
		 
		        }
		       String array = Arrays.toString(res);
        		return new Greeting(counter.incrementAndGet(),
                            String.format(template,array));
    }
}