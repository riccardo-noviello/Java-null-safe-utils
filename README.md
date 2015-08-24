Java-null-safe-utils
====================

<h3>Description:</h3>
<p>
This simple Java Library intends to provide Java code with a shorter, safer syntax to access Object's properties avoiding <code>NullPointerExceptions</code>.
</p>

<p>
Whilst Java 8 has introduced some new features to handle null values (see <a href="http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html">Optional</a>) this is still far from being concise and useful in my opinion. 
</p>

<p>Unfortunately a <b>safe navigation operator</b> available in languages such as C# and Groovy is still not available in Java. If it wasn't clear, I am talking about this:
<code>String version = computer?.getSoundcard()?.getUSB()?.getVersion();</code></p>

Rather than: 

<pre>String version = "UNKNOWN";
if(computer != null){
  Soundcard soundcard = computer.getSoundcard();
  if(soundcard != null){
    USB usb = soundcard.getUSB();
    if(usb != null){
      version = usb.getVersion();
    }
  }
}</pre>

<h3>Maven:</h3>
```<repositories>
  <repository>
    <id>rik-snapshots</id>
    <url>https://github.com/rik86/rik-mvn-repo/raw/master/snapshots</url>
  </repository>
</repositories>

...
<dependency>
         <artifactId>NullUtils</artifactId>
         <groupId>com.riccardonoviello</groupId>
         <version>1.0</version>
</dependency>
```

<h3>Usage:</h3>
There are three methods in the Utils, <b>isAccessible()</b>, <b>get()</b> and <b>getOr()</b>. It is very simple, you can use them as follows:

<p><b>1) "isAccessible()" Find out if a property is null</b></p>
<p>
<code>NullSafeUtils.isAccessible(computer, "soundcard", "usb", "version");</code></p>
<p>Having an Object company we can dig into the Object's properties "soundcard", "usb" and "version" safely since the null checks are all done for us. Finally we will be told if the property is indeed accessible (boolean).
</p>


<p><b>2) "get()" Get the property<b></p>

<p>
<code>NullSafeUtils.get(computer, "soundcard", "usb", "version");</code>
</p>

<p>
<b>An Example:</b></br>

<pre>
    
        Company company = buildCompany();
        String addressFromJava = null;
        String addressFromUtils = null;
        
        // in plain Java this would typically be
        if (company != null) {
            if (company.getDirector() != null) {
                if (company.getDirector().getAddress() != null) {
                  // do something with the Street
                    addressFromJava= company.getDirector().getAddress().getStreet();
                }
            }
        }
        
        // This is the same code using Nullutils
        addressFromUtils = (String) NullSafeUtils.get(company, "director", "address", "street");
        
        // We expect the same result
        assertEquals(addressFromJava, addressFromUtils);
    
</pre>
</p>


<p><b>3) "getOr()" Get the property or provide an alternative<b></p>

<p>
<code>NullSafeUtils.getOr(computer, "soundcard", "usb", "version").or("UNKNOWN");</code>
</p>

<b>An Example:</b></br>
<pre>
       String alternative = "N/A"; // some countries don't have postcode
        
        // the postoce is null so we are expecting to be returned the alternative
        assertEquals(alternative, NullSafeUtils.getOr(company, "director", "address", "postcode").or(alternative));     
</pre>
