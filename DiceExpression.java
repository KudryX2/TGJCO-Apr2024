import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DiceExpression {
    private String expression;

    private final String DELIMITERS = " ";
    private final String DICE_TOKEN = "d";
    private final String VALID_TOKEN = "([\\d\\+\\-\\*].*)";

    public DiceExpression(String expression) {
        this.expression = expression.trim(); // Trim the input string
    }

    /**
     * Validates the given expression.
     */
    public void validate(){
        String validationRegex = "^((\\d+d(2|3|4|6|8|10|12|20|100))|((d(2|3|4|6|8|10|12|20|100))))(\\s*([-+*])\\s*((\\d*\\.\\d+)|\\d+d(2|3|4|6|8|10|12|20|100)|\\d+))*$";
        if(!expression.matches(validationRegex))
            throw new RuntimeException("Input not valid");
    }

    /**
     * Calculates the value of the dice roll. The input should be a valid expression
     *
     * @param diceInput example d6 where is a dice with 6 sides. 2d8 are two dices of 8 sides
     * @return
     */
    public int evaluateDiceValue(String diceInput){
        int numOfRolls = 1;
        if (diceInput.charAt(0) != 'd') {
            numOfRolls = diceInput.charAt(0);
        }
        int sides = 0;
        if (diceInput.charAt(1) == 'd') {
            sides = diceInput.charAt(2);
        }
        DiceRoll diceRoll = new DiceRoll(numOfRolls, sides);
        return diceRoll.roll();
    }

    public String combineExpression(String s) {
        char action = '+';
        String trimmed = s.replaceAll("\\s+","");
        int total = 0;

        for (int i = 0; i < trimmed.length() - 1; i++) {
            char c = trimmed.charAt(i);
            if(!Character.isDigit(c))
            {
                action = trimmed.substring(i, i + 1).toCharArray()[0];
            }
        }

        List<String> temp = List.of(trimmed.split(String.valueOf("\\" + action)));

        if(action == '+')
        {
            total = Integer.valueOf(temp.get(0)) + Integer.valueOf(temp.get(temp.size()-1));
        }
        else if(action == '-')
        {
            total = Integer.valueOf(temp.get(0)) - Integer.valueOf(temp.get(temp.size()-1));
        }
        else if(action == '*')
        {
            total = Integer.valueOf(temp.get(0)) * Integer.valueOf(temp.get(temp.size()-1));
        }
        return String.valueOf(total);
    }

    /**
     * Use methods to break expression into tokens and evaluate expression
     */
    public int evaluate() {
        validate();
        final var splitExpression = Arrays.asList(expression.split(DELIMITERS));
        final var tokens= splitExpression.stream()
                .map(token ->
                        token.contains(DICE_TOKEN) ?
                                String.valueOf(evaluateDiceValue(token)) : token)
                .filter(token -> token.matches(VALID_TOKEN))
                .collect(Collectors.toList());

        int combinedExpressionResult = Integer.parseInt(combineExpression(String.join("", tokens)));
        System.out.println("Result: " + combinedExpressionResult);

        return combinedExpressionResult;
    }
}