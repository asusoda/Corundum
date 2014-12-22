package org.corundummc.types;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.utils.ListUtilities;

public abstract class CreatableType<S extends CreatableType<S, T>, T extends Typed<S>> extends IDedTypeWithData {
    protected CreatableType(int id, int data) {
        super(id, data);
    }

    static {
        /* TODO: if debugging mode is on, make sure that
         * 
         * 1) all the values of any given CreatableType subclass has corresponding values with the same name in their parent class,
         * 
         * 2) each CreatableType subclass has type parameters matching those in this class in order and type */
    }

    public T create() {
        try {
            return (T) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2].getClass().getConstructor().newInstance();
        } catch (ArrayIndexOutOfBoundsException | ClassCastException | InstantiationException exception) {
            throw new CIE(
                    "Someone did not parameterize this CreatableType subclass properly! CreatableTypes should have three type parameters: a self-parameterization S, a Minecraft Entity type parameter MC, and a Creatable instance type I; something seems to be wrong with this CreatableType's I parameter!",
                    "improperly parameterized CreatableType subclass", "subclass = " + getClass().getSimpleName(), "parameters = "
                            + ListUtilities.writeArray(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()));
        } catch (IllegalAccessException | SecurityException exception) {

        } catch (IllegalArgumentException | NoSuchMethodException exception) {

        } catch (InvocationTargetException exception) {

        }
    }

    public static final class UnimplementedCreatableTypeException extends CorundumException {
        private static final long serialVersionUID = 2906117248394808508L;

        public UnimplementedCreatableTypeException(CreatableType type) {
            super("No one implemented the create() method for this " + type.getClass().getSimpleName() + " " + type.getName() + "!", "unimplemented create() call");
        }

    }
}
