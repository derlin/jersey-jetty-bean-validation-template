# jersey-jetty-bean-validation-template

This is a starter template for a __Java EE RESTful application__ using an embedded Jetty Server.

## quick start

__run__:

 - clone the project: `git clone git@github.com:derlin/jersey-jetty-bean-validation-template.git`
 - from the commandline go to the root of the project and run the following command: `mvn jetty:run`
 - if you use an IDE, you can also run the `WebAppLauncher` class
 - open `http://localhost:8680/api`, it should display _it works!_.

__test__:

The simplest way is to use `curl`. The web service post method expect a json like this one:

```
{
  "timestamp": "2016-12-31T15:59:60+02:00", // date using the RFC 3339 standard
  "captor-id": "3", // a positive integer
  "address": "1.1.1.1", // optional: only numbers and dots
  "value": "3.4", // a string
  "token": "123456789-123456789-123456789-12", // a string of length 32
  "comment": "lala" // an optional comment, at most 1024 chars
}
```
For example, the following command should work:

```
curl localhost:8680/api -X POST --header "content-type:application/json" -d '{ "timestamp": "2016-12-31T15:59:60+02:00", "captor-id": "3", "value": "3.4", "token": "123456789-123456789-123456789-12" }'
```

while the following command should display an error (negative captor-id):
```
curl localhost:8680/api -X POST --header "content-type:application/json" -d '{ "timestamp": "2016-12-31T15:59:60+02:00", "captor-id": "-3", "value": "3.4", "token": "123456789-123456789-123456789-12" }' -v
```

--------------------------

### Technologies

The project uses the following technologies:

 - [Maven](https://maven.apache.org/what-is-maven.html): a module for dependency management;
 - [Jetty](http://www.eclipse.org/jetty/): a simple web server and servlet container that can be embedded;
 - [Jersey](https://jersey.java.net/): a RESTful Web Services framework conforming to the JAX-RS reference implementation;
 - [Jackson](http://wiki.fasterxml.com/JacksonHome): a multi-purpose Java library for processing JSON data format;


### Jetty Server

 This project contains everything needed to develop and run quickly a Java RESTful webserver. Moreover, it is configured
 to support the standard bean annotation validation.

 #### Embedded Jetty

 The jetty dependencies are listed in the `pom.xml`. The server can be started either from your IDE
 (by runnig the `WebAppLauncher` main method) or using the [mavenjetty plugin](http://www.eclipse.org/jetty/documentation/current/jetty-maven-plugin.html).

To configure jetty, have a look at the `ExampleApp` class, as well as the `webapp/WEB-INF/web.xml` file.
`ExampleApp` lists the packages to parse in search for jersey webservices (in this project, the `ch.derlin.jaxrs.ws` package) and
turn the bean validation features on.
The `web.xml` file registers the default servlet container, set the base path of the
webservices (the root `/` in this project) and pass the `ExampleApp` class as a parameter to the
servlet container.

The only things you would like to change (except for the class names of course), are:

 - the server port: declared both in the `pom.xml` and the `WebAppLauncher` class;
 - the validation support: to turn it off, just remove all the calls to the `properties()` method inside the `ExampleApp` constructor;
 - the webservices packages: you need to list all the packages containing web services in the `ExampleApp` constructor. Note that the parsing is
   recursive. By default, it will load the classes under `ch.derlin.jaxrs.ws`.


### Web Service and Java bean

The sample provides one web service, `ExampleService`, which supports GET and POST methods under the path `/api`.
The POST method accepts both JSON and XML and expects a __valid__ `InputExample` object (due to the `@Valid` annotation).

__standard validation annotations__

The `InputExample` class is a simple Java Bean, which shows different ways to use the standard validation annotations:

 - `@Null`, `@NotNull`: optional vs required parameters;
 - `@Size`, `@Min`, `@Max`: for the size of Strings/integers/floats;
 - `@Pattern`: specifying a special pattern (regex) which must be validated against the serialized field.


__json support and custom serializers__

The `@XmlRootElement` and `@XmlElement` annotations mark the classes/fields to be included in the XML/JSON. The optional `name` argument
allows you to specify another name for the field.

The `timestamp` field is interesting, since it shows you how to use a custom serializer. Have a look at the `@JsonSerialize` and `@JsonDeserialize` annotations
and the  `ch.derlin.jaxrs.utils.RFC3339JsonUtils` class. Here, the date is serialized/deserialized using the RFC 3339 format).  


### Static resources

In this example, any static resource under `src/main/webapp` is served (have a look at the `index.html` for more details.). To achieve this, two things:

 - for _mvn launch_: the `pom.xml` specifies a  resource directory,
 - for _IDE launch_: the `WebAppLauncher` get the path to the project sources and add it as the `resourceBase` (for it to work, `webapp` must be a resource directory in your project settings).
