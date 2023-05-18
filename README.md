# ObservationApi
Webflux spring boot 3 application demonstrates how to propagate traceId throw web service calls using WebClient

Run the spring boot application and then call: http://localhost:8787/edge/hello/Name
Check the logs:

2023-05-19T00:17:27.836 [INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,844df2957e2d8666] thread:[reactor-http-nio-3       ] c.example.observation.GreetingController.lambda$outerGreeting$2@66:
   Calling inner hello 
2023-05-19T00:17:27.841 [INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,5487f594a1a2e06d] thread:[reactor-http-nio-4       ] c.example.observation.GreetingController.greeting@50:
   {user-agent=ReactorNetty/1.1.5, host=localhost:8787, accept=*/*, traceparent=00-64668edfbf483366bbef6c12861fb0d5-f4b36a858da5e9dc-00} 
2023-05-19T00:17:27.841 [INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,af48a933b4b54143] thread:[reactor-http-nio-4       ] c.example.observation.GreetingController.lambda$greeting$1@52:
   Generating a String... 
2023-05-19T00:17:28.842 [INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,af48a933b4b54143] thread:[reactor-http-nio-4       ] c.e.observation.InnerGreetingService.getGreeting@14:
   create 1fdsdf 
2023-05-19T00:17:28.842 [INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,af48a933b4b54143] thread:[reactor-http-nio-4       ] com.example.observation.Greeting.<init>@21:
   create greeting 
