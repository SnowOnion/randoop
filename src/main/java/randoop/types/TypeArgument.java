package randoop.types;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * Represents a type argument of a parameterized type as described in
 * JLS (Section 4.5.1).
 * A type argument may be either a reference type or a wildcard.
 */
public abstract class TypeArgument {

  /**
   * Indicates whether this type argument is generic.
   *
   * @return true if this type argument is generic, false otherwise
   */
  public abstract boolean isGeneric();

  /**
   * Converts a {@code java.lang.reflect.Type} to a {@code TypeArgument}
   * object.
   *
   * @param type  the type of a type argument
   * @return the {@code TypeArgument} for the given type
   */
  public static TypeArgument forType(Type type) {
    if (type instanceof WildcardType) {
      return WildcardArgument.forType(type);
    } else {
      return ReferenceArgument.forType(type);
    }
  }

  /**
   * Indicates whether this type argument can be instantiated by the given
   * {@link GeneralType}.
   *
   * @param generalType  the type to test
   * @param substitution the substitution for this type (? do we need this?)
   * @return true if the type can instantiate this argument, false otherwise
   */
  public abstract boolean canBeInstantiatedAs(GeneralType generalType, Substitution substitution);
}