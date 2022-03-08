
# Mandelbrot Server

The server side of a project that renders the mandelbrot set on to an image.

Contains a get endpoint which takes in image height and width, max iterations for
mandelbrot set and list of ports that the calculations should be divided on to.
Then renders the image on a set of threads depending on the ports that was passed 
to the endpoint.


Run the client application on the same machine as this server application to interact
with the server.

Client application repo:
https://github.com/mikaellove/mandelbrot-client.git

For now the client and the server app only runs on the localhost.


## Documentation

Documentation of the code and of possible improvements can be found within the code.


## Run Locally

Clone the projects

```bash
  git clone https://github.com/mikaellove/mandelbrot-client.git
  git clone https://github.com/mikaellove/mandelbrot-server.git
```


Run the projects

```bash
  First start the server application in preffered IDE.
```
```bash
  Then run the client application(on this repo) in preffered IDE.
```


## Author

- [@mikaellove](https://github.com/mikaellove)

