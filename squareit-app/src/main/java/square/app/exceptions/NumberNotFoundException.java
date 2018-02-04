package square.app.exceptions;

public class NumberNotFoundException extends ObjectNotFoundException {

  public NumberNotFoundException(Class clazz, Object id) {
    super(clazz, id);
  }
}
