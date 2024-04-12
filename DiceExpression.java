public class DiceExpression {
    private String expression;

    public DiceExpression(String expression) {
        this.expression = expression.trim(); // Trim the input string
    }

    public void validate(String input){
        String validationRegex = "^((\\d+d(2|3|4|6|8|10|12|20|100))|((d(2|3|4|6|8|10|12|20|100))))(\\s*([-+*])\\s*((\\d*\\.\\d+)|\\d+d(2|3|4|6|8|10|12|20|100)|\\d+))*$";
        if(!input.matches(validationRegex))
            throw new RuntimeException("Input not valid");
    }

    public int evaluateDiceValue(String diceInput){

    }

    public int combineExpression(String partialExpression){

    }

    public void evaluate() {



    }
}