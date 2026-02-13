import java.util.Arrays;
import java.util.Deque;

public class OpDup extends ScriptOp {

    @Override
    public void execute(Deque<byte[]> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("Error: OP_DUP en pila vacía.");
        }

        byte[] top = stack.peek();
        stack.push(Arrays.copyOf(top, top.length));
    }
}
