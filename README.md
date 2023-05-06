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

[![Fly Deploy Production](https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/actions/workflows/fly.prod.yml/badge.svg)](https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/actions/workflows/fly.prod.yml)

[![Fly Deploy Staging](https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/actions/workflows/fly.staging.yml/badge.svg)](https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/actions/workflows/fly.staging.yml)

## Authors

- Nabil Ouchaou
- Mohamed Bouezmarni
- Yacine Kahlis
- Sandrine Comeau
- Alexandre Dicaire


## Documentation
Please also read our code of conduct
https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/blob/05cb65d154e4efb7e4b678a12a1ab165010988e1/CODE_OF_CONDUCT 

and our guide to contributing
https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/blob/05cb65d154e4efb7e4b678a12a1ab165010988e1/CONTRIBUTING.md 


## License

This project is licensed under the MIT License - see the LICENSE.md file for details
https://github.com/ScarfaceDeLaStreet-Equipe22/floppa/blob/05cb65d154e4efb7e4b678a12a1ab165010988e1/LICENSE 