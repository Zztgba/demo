class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}

abstract class Inning {
    Inning() throws BaseballException {}
//    void event () throws BaseballException {
//    }
    abstract void event();
    abstract void atBat() throws Strike, Foul;

    void walk() {} // Throws nothing
}

class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}
class Rb extends RainedOut{}

interface Storm {
    void event() throws RainedOut;
    void rainHard() throws RainedOut;
}

public class StormyInning extends Inning
        implements Storm {

    StormyInning() throws RainedOut, BaseballException {}

    StormyInning(String s) throws Foul,BaseballException {}

    // Regular methods must conform to base class:
//    @Override
//    void walk(){} throws PopFoul {} //Compile error
    // Interface CANNOT add exceptions to existing
    // methods from the base class:
//! public void event() throws RainedOut {}
    // If the method doesn't already exist in the
    // base class, the exception is OK:
    @Override
    public void rainHard() throws RainedOut {}
    // You can choose to not throw any exceptions,
    // even if base version does:
    @Override
    public void event() {}
    // Overridden methods can throw
    // inherited exceptions:
    @Override
    void atBat() throws PopFoul {}
    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch(PopFoul e) {
        } catch(RainedOut e) {
        } catch(BaseballException e) {}
        // Strike not thrown in derived version.
        try {
            // What happens if you upcast?
            Inning i = new StormyInning();
            i.atBat();
            // You must catch the exceptions from the
            // base-class version of the method:
        } catch(Strike e) {
        } catch(Foul e) {
        } catch(RainedOut e) {
        } catch(BaseballException e) {}
    }
} ///:~