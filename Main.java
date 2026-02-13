import java.security.MessageDigest;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean trace = true;

        ScriptInterpreter interpreter = new ScriptInterpreter(trace);

        byte[] signature = "firma".getBytes();
        byte[] pubKey = "clavePublica".getBytes();

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] sha = sha256.digest(pubKey);
        byte[] pubKeyHash = Arrays.copyOfRange(sha, 0, 20);

        List<ScriptOp> script = List.of(
            new PushData(signature),
            new PushData(pubKey),
            new OpDup(),
            new OpHash160(),
            new PushData(pubKeyHash),
            new OpEqualVerify(),
            new OpCheckSig());


        boolean result = interpreter.execute(script);

        System.out.println("Validación final: " + result);
    }
}
