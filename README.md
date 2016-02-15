# Birthday-greetings-kata

This is my version of the [exercise](http://matteo.vaccari.name/blog/archives/154) created by Matteo Vaccari. The purpose of this kata is to learn about the [Hexagonal Architecture](http://alistair.cockburn.us/Hexagonal+architecture).

I've done this kata from scratch in TDD.

## Setup

To import the project in Eclipse, just select 

File -> Import -> Maven -> Existing Maven Projects

I've used Eclipse Spring Tool Suite to build the application.

## How to run it
 
This application will send email over an SMTP serve on localhost. 
If you don't have an SMTP server installed on your machine, you can use a fake SMTP server, that is an application that intercepts all your outgoing messages and lets you display them.

I've successfully used [FakeSMTP](https://nilhcem.github.io/FakeSMTP/). 

Once you have your fake-or-real SMTP server started, run the main() in class BirthdayService.  