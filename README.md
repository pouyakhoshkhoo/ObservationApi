# ObservationApi
Webflux spring boot 3 application demonstrates how to propagate traceId throw web service calls using WebClient

Run the spring boot application and then call: http://localhost:8787/edge/hello/Name

# Check the logs:

[INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,844df2957e2d8666] 
   Calling inner hello 
 
[INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,5487f594a1a2e06d]
   {user-agent=ReactorNetty/1.1.5, host=localhost:8787, accept=*/*, traceparent=00-64668edfbf483366bbef6c12861fb0d5-f4b36a858da5e9dc-00} 
 
[INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,af48a933b4b54143]
   Generating a String... 

[INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,af48a933b4b54143]
   create Name 

[INFO] requestId:[64668edfbf483366bbef6c12861fb0d5,af48a933b4b54143]
   create greeting 
