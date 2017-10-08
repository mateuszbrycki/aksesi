## Aksesi
["Get Noticed 2017"](http://dajsiepoznac.pl) project.

### Build
[![Build Status](https://travis-ci.org/mateuszbrycki/aksesi.svg?branch=master)](https://travis-ci.org/mateuszbrycki/aksesi)

### Main idea
Aksesi's main idea is to allow authenticate a user with a password which consists of characters and gestures. 

![alt text](http://blog.mateuszbrycki.com/wp-content/uploads/2017/03/lines-keys.gif "Aksesi password example")
 
Aksesi will be easy to configure, even in the existing application. The client adds a simple JavaScript module, performs basic configuration and it is ready to use. The Aksesi module finds login form, provides gesture area, handles password and, at the end, redirects all of the login requests into the proxy. 

### Aksesi as a proxy
In the final version, Aksesi is expected to work as a proxy between client's frontend and backend application. 

![alt text](https://blog.mateuszbrycki.com/wp-content/uploads/2017/03/proxy-approach-ss-1024x341.png "Aksesi as a proxy")
 
In this model, Aksesi is responsible only for gesture conversion. Its logic doesn’t depend on any other application. A user sends the authentication request to the Aksesi Proxy, and then the proxy sends the request to the back-end application. It means that the user is not able to see a converted password. Another advantage of this solution is that the back-end application doesn’t know about the proxy so it doesn’t matter if application e.g. uses Single Sign-On mechanism.

### Security
Aksesi provides the new level of passwords security. 

#### Password length
Despite that Aksesi makes passwords longer, users still see the same length. 
When a user registers an account or login into the existing one, the proxy will convert gesture into string representation which length will be configurable, like below:

```html
a-<line1>-b-<line2>-c 
 conversion 
a(string representation of line1)b(string representation of line2)c
```

For the user, the password has the same length: 3 characters and 2 gestures but for the application/database it is a string consisting of many more characters. 

#### Capturing HTTP request
A web browser sends the password as a:

```html
character-(set-of-points)-character-(set-of-points)-character
```

If an attacker doesn’t have access to the converting application, then sets of points are useless. Finding textual gesture's representations will be very time-consuming or almost impossible.

#### Passwords stored in a browser
Aksesi's frontend module inserts gestures into an input as the '~' character. The client's browser will not save the points so it will be impossible to re-use a stored password. Note that this is one of the drawbacks when using mixed passwords.

### Author & contact
 Author: Mateusz Brycki
 
 Mail: mateuszbrycki@outlook.com 
 
 Website: [mateuszbrycki.com](http://mateuszbrycki.com)
