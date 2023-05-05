# Floppa

Floppa is a pateform made to let users sell their products and to make offers on other people's products.

## Prerequisites

- Java 11
- Maven

To test with database 
- MongoDB
- Docker 

## Commands

### Build

```
mvn clean install
```

### Execute

```
mvn exec:java
```


### Execute

```
docker build . -t floppa
docker run --rm -it  -p 18080:8080 floppa
```

### Pipelines
[![Actions Status](https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/workflows/build/badge.svg)](https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/actions)


## Authors

- Nabil Ouchaou
- Mohamed Bouezmarni
- Yacine Kahlis
- Sandrine Comeau
- Alexandre Dicaire


## License

This project is licensed under the MIT License - see the LICENSE.md file for details