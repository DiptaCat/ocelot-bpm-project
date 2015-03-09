Ocelot BPMS
===================
Ocelot BPMS project aims an extension over the emergent Developer-Friendly BPM systems. Moreover, this project follows the microservice architecture trend, where each application has a single responsibility. Initially, two web applications are offered : Ocelot MT and Ocelot XE.

##Ocelot Modeling Tool (MT)
Ocelot MT allows users to create and edit BPMN models, and export them as XML and/or as svg files. The modeling tool has been implemented using the bpmn.io library. This library is still under development but has been very useful and helped us to achieve a good functionality and results. Ocelot MT provides the user with a palette with several items. One of its main features is that it can be configured according to the user needs or way of working.

The palette includes 5 levels by default, being level 1 the most basic where the simplest elements are shown and level 5 the most complete, where the user can access all the existing elements. It is usually enough to use levels 1 and two in order to model a process, that’s why using these filters the work is streamlined and the palette is adapted to the user needs, being easier to use it.

It is also possible to create custom palette items. The user can add custom properties to them, select their category and choose the level where they will belong, everything in a very visual way. With these features we achieved a flexible palette which can be easily customized by each user.

Finally, a REST API has been added in order to query the previously saved models. Through this service the user can list the available models and get information about any of them. When querying a single model the application will send all the related information including the BPMN model in xml format so the user can deploy it wherever it want.

##Ocelot eXecution Environment (XE)
On the other side there is the Ocelot XE application. This application allows the user to execute and manage a process. In order to do that, the user can import a model form a xml file or to list the models previously created in the Ocelot MT modeler through the REST API. Once the process is being executed, this application also allows the task management.

This application allows the user to execute the process in the Camunda platform in a minimalist way. The user can deploy the models, manage them, manage tasks, etc. All these services are available through the RESTfull API given by Camunda. It is a simple application, easy to use and to extend in order to adapt it to the user needs. Moreover, a user management system has not been implemented so the user can use the one he/she was using previously or to implement his/her own service.

| [![Diputació de Tarragona](http://greachconf.com/wp-content/uploads/2014/01/diputacio-tarragona.png)](http://www.diputaciodetarragona.cat/)      | [![Universitat Rovira i Virgili](http://www.urv.cat/media/gif/logo_urv.png)](http://www.urv.cat)     
| :-------------: |:-------------:| 
