import java.util.Arrays;
import java.util.Deque;

public class PushData extends ScriptOp{
    private final byte[] data;

    public PushData(byte[] data) {
        this.data = data;
    }
    public void execute(Deque<byte[]> stack) {
        stack.push(Arrays.copyOf(data, data.length));
    }
}