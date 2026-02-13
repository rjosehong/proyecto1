import java.util.Arrays;
import java.util.Deque;

public class OpEqual extends ScriptOp {

    @Override
    public void execute(Deque<byte[]> stack) {
        if (stack.size() < 2) {
            throw new RuntimeException("Error: OP_EQUAL requiere 2 elementos.");
        }

        byte[] a = stack.pop();
        byte[] b = stack.pop();

        boolean equals = Arrays.equals(a, b);

        stack.push(equals ? new byte[]{1} : new byte[]{0});
    }
}
