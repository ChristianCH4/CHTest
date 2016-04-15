
import com.yoctopuce.YoctoAPI.*;

public class Demo {

    public static void main(String[] args) {
       
    	// CONNEXION AU SERVEUR VIRTUALHUB DISTANT
    	try {
            // connexion au serveur VirtualHub distant
            YAPI.RegisterHub("ch4pi1.ddns.net");
    		} 
    	catch (YAPI_Exception ex){
            System.out.println("Connexion au serveur VirtualHub impossible (" + ex.getLocalizedMessage() + ")");
            System.out.println("Vérifiez le fonctionnement de VirtualHub");
            System.exit(1);
    		}
    	
    	  // LISTE DES MODULES CONNECTES
        System.out.println(">>>>> Liste des modules connectés");
        YModule module = YModule.FirstModule();
        while (module != null) {
            try {
                System.out.println(module.get_serialNumber() + " (" + module.get_productName() + ")");
            }
            catch (YAPI_Exception ex) {
                break;
            }
            module = module.nextModule();  
        }
            
        YTemperature tsensor;

        if (args.length == 0) {
            tsensor = YTemperature.FirstTemperature();
            if (tsensor == null) {
                System.out.println("Le module n'est pas connecté");
                System.exit(1);
            }
        } else {
            tsensor = YTemperature.FindTemperature(args[0] + ".temperature");
        }
  
        // LECTURE DE LA TEMPERATURE
        System.out.println(" ");   
        System.out.println(" >>>>> Lecture du capteur 20 fois");
        System.out.println(" Température actuelle");
        int i;
        for (i=1; i<21; i++)
        {
        	// while (true)
        	//{
        		try 
        		{
        			System.out.println(i + ":  "+ tsensor.get_currentValue() + " °C");
        			//System.out.println("  (appuyer Ctrl-C pour arrêter)");
        			YAPI.Sleep(1000);
        		}
        		catch (YAPI_Exception ex)
        		{	
        			System.out.println("Le module n'est pas connecté");
        			break;
        		//}   
        		}
        }
      
        System.out.println(">>> C'est fini !!!!");
        YAPI.FreeAPI();
    }
}




