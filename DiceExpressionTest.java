import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiceExpressionTest {

    @Test
    void testSingleDice(){
        DiceExpression expression = new DiceExpression("2d6");
        long result = expression.evaluate();
        Assertions.assertEquals(11, result);
    }

    @Test
    void testSingleDiceAndNumber(){
        DiceExpression expression = new DiceExpression("d20 + 5");
        long result = expression.evaluate();
        Assertions.assertEquals(17, result);
    }

    @Test
    void testSingleDiceAndDecimal(){
        DiceExpression expression = new DiceExpression("3d4 * 1.5");
        long result = expression.evaluate();
        Assertions.assertEquals(12, result);
    }

    @Test
    void testMultipleDicesAndNumber(){
        DiceExpression expression = new DiceExpression("2d10 + 3d8 - 5");
        long result = expression.evaluate();
        Assertions.assertEquals(21, result);
    }

    @Test
    void testNotValidInput(){
        DiceExpression expression = new DiceExpression("2d13");
        Assertions.assertThrows(RuntimeException.class, () -> expression.evaluate());
    }

}
