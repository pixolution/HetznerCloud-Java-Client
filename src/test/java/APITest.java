import org.junit.Test;

import de.katzen48.hetznercloudjava.HetznerCloud;
import de.katzen48.hetznercloudjava.resources.ServerType;

import static org.junit.Assert.*;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'Startrekfreak' at '24.01.18 06:47' with Gradle 2.9
 *
 * @author Startrekfreak, @date 24.01.18 06:47
 */
public class APITest 
{
	private String token = "FooBar";
	
    @Test public void testAPIConnection() 
    {
        HetznerCloud cloud = new HetznerCloud.Builder().withToken(token).build();
        assertNotNull("getServers() should return some List", cloud.getServers());
        System.out.println(cloud.getServers().length);
        System.out.flush();
    }
    
    @Test public void testPricing() 
    {
        HetznerCloud cloud = new HetznerCloud.Builder().withToken(token).build();
        assertNotNull("getPricing() should return some Value", cloud.getPricing());
        for(ServerType type : cloud.getPricing().getServerTypes())
        {
        	System.out.println(type.getName());
            System.out.flush();
        }
    }
}
