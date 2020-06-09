package factory.example3;

import annotations.Find;
import annotations.WaitUntilPresent;
import components.example2.Component;
import components.example3.NextGenComponent;
import components.example3.NextGenWebComponent;
import driver.DriverManager;
import pageobject.example2.PageObject;
import pageobject.example3.NextGenPageObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class NextGenPageFactory {

    public static void processPageObjects(NextGenPageObject pageObject, DriverManager driverManager) {
        processObjects(pageObject, driverManager);
    }

    public static void processComponents(NextGenComponent component, DriverManager driverManager) {
        processObjects(component, driverManager);
    }

    private static void processObjects(Object object, DriverManager driverManager) {
        Class<?> klass = object.getClass();
        // process class level annotations
        if (object instanceof NextGenComponent) {
            processComponentAnnotations((NextGenComponent) object, klass.getDeclaredAnnotations());
        }
        // process current page object klass fields
        processFields(klass.getDeclaredFields(), object, driverManager);
        // go through all classes in hierarchy from current class to parent
        while (!(klass = klass.getSuperclass()).isAssignableFrom(NextGenPageObject.class) && !klass.isAssignableFrom(NextGenComponent.class)) {
            // process page object class fields
            processFields(klass.getDeclaredFields(), object, driverManager);
        }
    }

    private static void processFields(Field[] fields, Object owner, DriverManager driverManager) {
        Arrays.stream(fields)
              // filter only fields of type Component
              .filter(field -> NextGenComponent.class.isAssignableFrom(field.getType()))
              .forEach(field -> {
                  // create new instance of field
                  NextGenComponent component = createComponent(field, driverManager);
                  // set parent component
                  if (owner instanceof NextGenComponent) {
                      component.setParentComponent((NextGenComponent) owner);
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

    private static NextGenComponent createComponent(Field field, DriverManager driverManager) throws ClassCastException {
        NextGenComponent component;
        try {
            component = field.getType().asSubclass(NextGenComponent.class).getConstructor(DriverManager.class).newInstance(driverManager);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Exception while creating component field.", e);
        }
        // process fields annotations
        processComponentAnnotations(component, field.getDeclaredAnnotations());
        return component;
    }

    private static void processComponentAnnotations(NextGenComponent component, Annotation[] annotations) {
        Map<Class<? extends Annotation>, Annotation> annotationMap = Arrays.stream(annotations)
                                                                           .collect(Collectors.toMap(Annotation::annotationType, annotation -> annotation));
        processFindAnnotation(annotationMap.get(Find.class), component);
        processWaitUntilPresentAnnotation(annotationMap.get(WaitUntilPresent.class), component);
    }

    private static void processFindAnnotation(Annotation find, NextGenComponent component) {
        if (find != null) {
            component.setXpathLocator(((Find) find).xpath());
        }
    }

    private static void processWaitUntilPresentAnnotation(Annotation waitUntilPresent, NextGenComponent component) {
        if (waitUntilPresent != null) {
            component.waitUntilPresent();
        }
    }
}
