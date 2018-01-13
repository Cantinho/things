# things
A project about things.



# How To Install:
It was used Lombok plugin. 
You must install it in your IDE; 
enable Annotation Processing;
and install the Latest Maven on your system and configure IDE to use it.

By default, lombok adds ```@javax.annotation.Generated("lombok")``` to all generated methods.
Since the new module system, this annotation is no longer standard available.
There are two ways to fix this:
 1.  Add the following line to you project ```lombok.config``` file:
   ```lombok.addJavaxGeneratedAnnotation = false```
 2.  Add ```--add-modules=java.xml.ws.annotation``` to your javac command line parameters.
