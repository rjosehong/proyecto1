import java.util.Arrays;
import java.util.Deque;

public class OpEqualVerify extends ScriptOp{
    public void execute(Deque<byte[]> stack) {
        if (stack.size() < 2) {
            throw new RuntimeException("Error: OP_EQUALVERIFY requiere al menos 2 elementos.");
        }

        byte[] item1 = stack.pop();
        byte[] item2 = stack.pop();

        if (!Arrays.equals(item1, item2)) {
            throw new RuntimeException("Error: OP_EQUALVERIFY falló (los elementos no son iguales).");
        }
    }
}