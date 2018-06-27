# Android-CleanArchitecture app
An Android app designed using [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) and [MVP](https://medium.com/mobiwise-blog/android-basic-project-architecture-for-mvp-72f4b33252d0#.7lkkhy45q).

## Description
This is an Android app designed for learning Clean Architecture and MVP patterns.

## Architecture
The code is divided in 3 layers, Data, Domain and Presentation. The Domain is structured following the main premises of [Clean Architecture](https://github.com/mp911de/CleanArchitecture "Clean Architecture"). The app follows the [dependency inversion principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle) using the protocol oriented approach that Java has on its foundations. The app has unit test for each layer.

### Domain
On this layer belongs all the classes which main concern is handling the business logic of the app. Here you will see all the interactors (user cases) implementations as well.

#### - Entities
On this group you will find the enterprise business rules to be consumed by the app. Encapsulates the most general and high-level rules. They are the least likely to change when something external changes.

#### - Interface adapters
This is a set of adapters that convert data from the format most convenient for the use cases and entities, to the format most convenient for some external agency. Presenters and Controllers belong here.

#### - Use Cases
The code in this layer contains application specific business rules. Each use case is represented by a protocol, the internal implementation is separated from the interface. Having a protocol per Use Case enforces the [Single Responsibility Principle](https://en.wikipedia.org/wiki/Single_responsibility_principle), facilitates unit testing and improves traceability.
The objects on this group relies on the entities and the services via interfaces (to preserve dependency inversion principle) and returns the data using [DTOs](https://en.wikipedia.org/wiki/Data_transfer_object) to avoid exposing the entity layer and to model the data in a convenient way to be consumed by the App.

## Presentation
The objects in this layer have the responsibilities described in the [MVP](https://medium.com/mobiwise-blog/android-basic-project-architecture-for-mvp-72f4b33252d0#.7lkkhy45q) architectural pattern. The Presenter relies on the UseCases to get the data and model in a convenient way to be shown in the UI.

## Data
This layer retrieves all data that is needed in the app through a UserRepository implementation. With this implementation the data origin is transparent for the client.

## License
©2017 Globant.
