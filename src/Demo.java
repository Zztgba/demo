/**
 * @Author yangfangyu
 * @Date 2019-6-21 11:24
 */
public class Demo {
    public static void main(String[] args) {
        Object modelInfo = "123";
        try {

            System.out.println();
            if (modelInfo instanceof Class) {
                System.out.println("-------");
            } else {
                System.out.println("++");
            }
        }
        catch(Exception e){}
    }
}
