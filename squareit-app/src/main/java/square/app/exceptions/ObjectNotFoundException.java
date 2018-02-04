package square.app.exceptions;

public class ObjectNotFoundException extends RuntimeException {

  private Class<?> entityNotFound;

  public ObjectNotFoundException(Class<?> entityNotFound, Object id) {
    super(String.format("%s with id = '%s' does not exist", entityNotFound.getSimpleName(), id));
    this.entityNotFound = entityNotFound;
  }

  public Class<?> getEntityNotFound() {
    return entityNotFound;
  }
}
