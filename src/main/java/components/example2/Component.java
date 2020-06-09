package components.example2;

import driver.DriverManager;
import factory.example2.PageFactory;

public abstract class Component {
    protected final DriverManager driverManager;
    private Component parentComponent;

    public Component(DriverManager driverManager) {
        this.driverManager = driverManager;
        PageFactory.processComponents(this, driverManager);
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    public boolean hasParent() {
        return parentComponent != null;
    }
}
