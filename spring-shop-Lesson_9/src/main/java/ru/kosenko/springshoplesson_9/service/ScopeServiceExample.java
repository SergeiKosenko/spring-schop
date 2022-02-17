package ru.kosenko.springshoplesson_9.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Random;

@Service
@SessionScope
// @RequestScope
public class ScopeServiceExample {

  private int randomInt;

  public ScopeServiceExample() {
    randomInt = new Random().nextInt(100);
  }

  public int getRandomInt() {
    return randomInt;
  }
}
