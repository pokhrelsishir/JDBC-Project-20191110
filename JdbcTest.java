public class JdbcTest
{
    public static void main(String[] args)
    {
	try {
	    System.out.println("Loading driver...");
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("Driver loaded!");
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("Cannot find the driver in the classpath!", e);
	}
    }
}
