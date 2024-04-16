package ru.vitasoft.statusrouteservicelibrary.exception;



public class CustomInternalServerErrorException extends RuntimeException {

  public CustomInternalServerErrorException(String message) {
    super(message);
  }
}