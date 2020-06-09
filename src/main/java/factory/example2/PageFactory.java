package factory.example2;

import components.example2.Component;
import driver.DriverManager;
import pageobject.example2.PageObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class PageFactory {

    public static void processPageObjects(PageObject pageObject, DriverManager driverManager) {
        processObjects(pageObject, driverManager);
    }

    public static void processComponents(Component component, DriverManager driverManager) {
        processObjects(component, driverManager);
    }

    private static void processObjects(Object object, DriverManager driverManager) {
        Class<?> klass = object.getClass();
        // process current page object klass fields
        processFields(klass.getDeclaredFields(), object, driverManager);
        // go through all classes in hierarchy from current class to parent
        while (!(klass = klass.getSuperclass()).isAssignableFrom(PageObject.class) && !klass.isAssignableFrom(Component.class)) {
            // process page object class fields
            processFields(klass.getDeclaredFields(), object, driverManager);
        }
    }

    private static void processFields(Field[] fields, Object owner, DriverManager driverManager) {
        Arrays.stream(fields)
              // filter only fields of type Component
              .filter(field -> Component.class.isAssignableFrom(field.getType()))
              .forEach(field -> {
                  // create new instance of field
                  Component component = createComponent(field, driverManager);
                  // set parent component
                  if (owner instanceof Component) {
                      component.setParentComponent((Component) owner);
                  }
                  // set object instance into variable declaration in class
                  field.setAccessible(true);
                  try {
                      field.set(owner, component);
                  } catch (IllegalAccessException e) {
                      throw new RuntimeException("Could not set new instance for component field.", e);
                  }
              });
    }

    private static Component createComponent(Field field, DriverManager driverManager) throws ClassCastException {
        Component component;
        try {
            component = field.getType().asSubclass(Component.class).getConstructor(DriverManager.class).newInstance(driverManager);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Exception while creating component field '" + field.getType() + " : " + field.getName() + "'", e);
        }
        return component;
    }
}
