package entity;

public class Topping {

        private String name;
        private boolean isDouble;

        public Topping(String name,boolean isDouble){
            this.name = name;
            this.isDouble = isDouble;
        }

    @Override
    public String toString() {
        return name + " - " + isDouble;
    }

    public String getName() {
        return name;
    }

    public boolean isDoubleOrder() {
        return isDouble;
    }
}
