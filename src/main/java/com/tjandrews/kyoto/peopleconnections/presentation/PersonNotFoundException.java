package com.tjandrews.kyoto.peopleconnections.presentation;

public class PersonNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 6514964371390315591L;

  PersonNotFoundException(Integer id) {
    super("Could not find person " + id);
  }

  PersonNotFoundException() {
    super("Could not find person with given id");
  }
  
}
