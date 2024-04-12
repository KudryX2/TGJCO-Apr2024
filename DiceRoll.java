import java.util.Random;
import java.util.stream.IntStream;

public class DiceRoll {

    private int numOfRolls;
    private int sides;

    public DiceRoll(int numOfRolls, int sides) {
        this.numOfRolls = numOfRolls;
        this.sides = sides;
    }

    // Static method to roll a die with a specified number of sides
    public int roll() {
        Random rnd = new Random();
        return IntStream
                .range(0, numOfRolls)
                .map(sides -> rnd.nextInt(sides))
                .sum();
    }

}