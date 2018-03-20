import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by xiang.wei on 2018/3/21
 *
 * @author xiang.wei
 */
public class CalculatorTest  extends TestCase {
    public void testFail(){
        fail("失败测试");
    }
    public void testAdd()
    {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        // 判断方法的返回结果
        Assert.assertEquals(3, result);// 第一个参数是期望值，第二个参数是要验证的值
    }

    public void testSubtract()
    {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(1, 2);
        // 判断方法的返回结果
        Assert.assertEquals(-1, result);// 第一个参数是期望值，第二个参数是要验证的值

    }

    public void testMultiply()
    {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(2, 3);
        // 判断方法的返回结果
        Assert.assertEquals(6, result);// 第一个参数是期望值，第二个参数是要验证的值

    }

    public void testDivide() {
        Calculator calculator = new Calculator();
        int result = calculator.divide(12, 3);
        // 判断方法的返回结果
        Assert.assertEquals(4, result);// 第一个参数是期望值，第二个参数是要验证的值
    }

}
