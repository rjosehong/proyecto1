import java.util.Deque;

public class OpCheckSig extends ScriptOp{
    public void execute(Deque<byte[]> stack) {
        if (stack.size() < 2) {
            throw new RuntimeException("Error: OP_CHECKSIG requiere firma y llave pública.");
        }

        byte[] pubKey = stack.pop();
        byte[] signature = stack.pop();

        boolean isValid = signature.length > 0; 

        byte[] result = isValid ? new byte[]{1} : new byte[]{0};
        stack.push(result);
    }
}