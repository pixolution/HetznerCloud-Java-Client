# Java Client for HetznerCloud [![](https://jitpack.io/v/Katzen48/HetznerCloud-Java-Client.svg)](https://jitpack.io/#Katzen48/HetznerCloud-Java-Client)
## **Installation**
### **Maven**
Just put this into your pom.xml:
```xml
<groupId>de.katzen48</groupId>
<artifactId>HetznerCloud-Java-Client</artifactId>
<version>VERSION</version>
  
<name>Jitpack/name>
<url>https://jitpack.io</url>
```
### **Gradle**
Just put this into your build.gradle:
```java
repositories {
	maven {
		url "https://jitpack.io"
	}
}

dependencies {
	compile group: 'de.katzen48', name: 'HetznerCloud-Java-Client', version: 'VERSION'
}
```
----------
## **Usage**

### **Getting Started**
To use the Client, create an Instance of HetznerCloud using the Builder.
```java
HetznerCloud cloud = new HetznerCloud.Builder().withToken(token).build();
```

Now you can start by getting some values to test your Application.
```java
HetznerCloud cloud = new HetznerCloud.Builder().withToken(token).build();
Pricing pricing = cloud.getPricing()

for(ServerType type : pricing.getServerTypes())
{
	System.out.println(type.getName());
}
```
This will get us the Names of all available Servers.


### **Docs**
You can find the docs [here](https://jitpack.io/com/github/katzen48/HetznerCloud-Java-Client/master-SNAPSHOT/javadoc/).
They will be updated automaticly, so don't worry about it.

----------
## License
Copyright (c) 2018 Katzen48

You are free to use this software for commercial and non-commercial cases. 
This software is licensed under the **GNU General Public License 3**.