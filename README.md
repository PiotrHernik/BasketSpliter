# BasketSpliter

Let's imagine we're running an online supermarket. Over time, we've expanded our product range with various items. Currently, not all products can be delivered by our delivery van. We also offer products from external suppliers that require specialized courier services for delivery. Therefore, we sell products that can be delivered by different suppliers, but not necessarily the same ones.

We want to split the customer's basket into delivery groups. The BasketSpliter library divides the basket optimally to minimize the number of deliveries. Additionally, with the minimal number of suppliers, we create the largest possible delivery groups, as customers tend to prefer purchasing items from the largest delivery group.


## Input and Output:

• In its **constructor**, BasketSpliter takes a path to a JSON config file containing a map where the key is the product name and the value is a list of possible delivery methods for that product:

	BasketSpliter basketSpliter = new BasketSpliter("path_to_config_file");
Config file:

	{
    "Carrots (1kg)": ["Express Delivery", "Click&Collect"],
    "Cold Beer (330ml)": ["Express Delivery"],
    "Steak (300g)": ["Express Delivery", "Click&Collect"],
    "AA Battery (4 Pcs.)": ["Express Delivery", "Courier"],
    "Espresso Machine": ["Courier", "Click&Collect"],
    "Garden Chair": ["Courier"]
    }


• The **split(List<String>)** method takes a list of items in the basket as an argument and returns an optimal split in the form of a map, where the key is the supplier and the value is a list of products, e.g.:

	[
	"Steak (300g)", "Carrots (1kg)", "Soda (24x330ml)", "AA Battery (4 Pcs.)", "Espresso Machine", "Garden Chair"
	]

#### Sample output:
	{
	"Express Delivery": ["Steak (300g)", "Carrot (1kg)", "Cold Beer (330ml)", "AA Battery (4 Pcs.)"],
	"Courier": ["Espresso Machine", "Garden Chair"]
	}

------------

## Running Instructions:

**Download the .jar file:**

• Download the existing .jar file located at:

	*target/BasketSpliter-1.0-SNAPSHOT.jar*
• Download the entire repository and in the terminal type: 

	mvn clear package
**Integrate with Maven project:**
• In IntelliJ, easily do it via 

	File -> Project Structure -> Libraries -> (click ‘+’) -> select the path to your .jar file -> OK
• Or, in the terminal, execute the command:

	mvn install:install-file -Dfile=<path_to_jar> -DgroupId=com.ocado.basket -DartifactId=BasketSpliter - Dversion=1.0.0 -Dpackaging=jar
	
and add the dependency to the pom.xml file:

	<dependency>
	<groupId>com.ocado.basket</groupId>
	<artifactId>basket_splitter</artifactId>
	<version>1.0.0</version>
	</dependency>

**If it's a regular Java application, not a Maven project:**

• Compile your Java code along with the basket_splitter.jar:

	javac -cp <path_to_jar> Your_App.java
• Run your application:

	java -cp <path_to_jar> Your_App.java

That's it. To use this library, create a BasketSpliter class, and then use the split(List<String> clientBasket) method, which will return a map with the optimal split.

------------


## The Set Cover Problem:

At the core of this library lies the Set Cover Problem. We want to cover the entire universe, represented as U = {1,2,3,…,n}, with the minimal number of subsets. This is an *NP-hard problem*, meaning it's a computational problem whose solution is at least as difficult as that of any problem in the entire universe.

	U = {1,2,3,4,5,6}
	S = {{1,2,3,4},{1,3,5},{1,2,3,5},{1,2,3,6},{2,4,6}}
This set can be covered in several ways:

	 {{1,2,3,5},{2,4,6}}
	 {{1,3,5},{2,4,6}}
	 {{1,2,3,4},{1,2,3,5},{1,2,3,6}}

However, we're interested in the first solution since it has only two subsets (suppliers) and compared to the second solution, it contains a larger subset (we can create a larger delivery).
