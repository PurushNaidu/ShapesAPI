1) Shapes API is a maven build project.
2) Use the below command to build this source.
     mvn clean install
3) Navigate the target folder and run the springboot app using below command.
    java -jar ShapesAPI-0.0.1-SNAPSHOT.jar
4) After app has been started, from using the postman.
    i) Testing Save Shape
         endpoint : localhost:8000/shape-api/save
         method   : POST
         Sample request: 
                {
   			"name": "Square1",
    			"type": "SQUARE",
    			"coordinates": {
      			  "X1": 20,
      			  "X2": 50,
      			  "Y1": 30,
      			  "Y2": 60
    					}
			}

    ii) Testing fetch all Shapes
         endpoint : localhost:8000/shape-api/shapes
         method   : GET