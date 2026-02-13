import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Deque;

public class OpHash160 extends ScriptOp {

    @Override
    public void execute(Deque<byte[]> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("Error: OP_HASH160 en pila vacía.");
        }

        try {
            byte[] data = stack.pop();

            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] shaHash = sha256.digest(data);

            byte[] hash160 = Arrays.copyOfRange(shaHash, 0, 20);

            stack.push(hash160);

        } catch (Exception e) {
            throw new RuntimeException("Error en OP_HASH160", e);
        }
    }
}
