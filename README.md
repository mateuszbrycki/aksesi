## aksesi
["Get Noticed 2017"](http://dajsiepoznac.pl) project.

### Main idea
Aksesi's main idea is to allow authenticate a user with password which consists of characters and gestures. 

![alt text](http://blog.mateuszbrycki.com/wp-content/uploads/2017/03/lines-keys.gif "Aksesi password example")
 
Aksesi will be easy to configure, even in the existing application. A user adds simple JavaScript module, performs basic configuration and it is ready to use. The Aksesi module finds login form, provides gesture area, handles password and, at the end, redirects all of the login requests into the proxy.
 
### Aksesi as a proxy
In the final version, Aksesi is expected to work as a proxy between client's frontend and backend application. 

 ![alt text](https://blog.mateuszbrycki.com/wp-content/uploads/2017/03/proxy-approach-ss-1024x341.png "Aksesi as a proxy")
 
In this model, Aksesi is responsible only for gesture conversion and its logic doesn’t depend on any other application. Secondly, a user sends the authentication request to the Aksesi Proxy, and then the proxy sends the request to the back-end application. It means that user is not able to see a converted password. Another advantage of this solution is that the back-end application doesn’t know about the proxy so it doesn’t matter if application e.g. uses Single Sing On mechanism.

### Security
Aksesi provides the new level of passwords security. 

#### Password length
Aksesi makes passwords longer whereas users see the same length. 
When a user registers account or login into existing one, the proxy will convert gesture into string representation which length will be configurable, like below:

```html
a-<line1>-b-<line2>-c 
 conversion 
a(string representation of line1)b(string representation of line2)c
```

For the user password has the same length: 3 characters and 2 gestures but for the application/database it is string consisting of many characters. 

#### Capturing http request
A web browser sends the password as a:

```html
character-(set-of-points)-character-(set-of-points)-character
```

If someone doesn’t have access to the converting application, then sets of points are useless because it is tremendously hard or almost impossible to find textual gestures representation.

#### Passwords stored in the browser
Aksesi's frontend module inserts gestures into input as the '~' character. With this approach, passwords stolen from browser storage are useless because an attacker doesn't know how the gestures look like.  

### Author & contact
 Author: Mateusz Brycki
 
 Mail: mateuszbrycki@outlook.com 
 
 Website: [mateuszbrycki.com](http://mateuszbrycki.com)
