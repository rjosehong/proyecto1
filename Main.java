import java.security.MessageDigest;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        boolean trace = true;


        System.out.println("\n**P2PKH Correcto**");

        ScriptInterpreter interpreter1 = new ScriptInterpreter(trace);

        byte[] signature = "firma".getBytes();
        byte[] pubKey = "clavePublica".getBytes();

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] sha = sha256.digest(pubKey);
        byte[] pubKeyHash = Arrays.copyOfRange(sha, 0, 20);

        List<ScriptOp> scriptCorrect = List.of(
            new PushData(signature),
            new PushData(pubKey),
            new OpDup(),
            new OpHash160(),
            new PushData(pubKeyHash),
            new OpEqualVerify(),
            new OpCheckSig()
        );

        boolean result1 = interpreter1.execute(scriptCorrect);
        System.out.println("Resultado final (correcto): " + result1);

        System.out.println("\n**P2PKH Incorrecto**");

        ScriptInterpreter interpreter2 = new ScriptInterpreter(trace);

        byte[] badSignature = new byte[]{};

        List<ScriptOp> scriptIncorrect = List.of(
            new PushData(badSignature),
            new PushData(pubKey),
            new OpDup(),
            new OpHash160(),
            new PushData(pubKeyHash),
            new OpEqualVerify(),
            new OpCheckSig()
        );

        boolean result2 = interpreter2.execute(scriptIncorrect);
        System.out.println("Resultado final (incorrecto): " + result2);


        System.out.println("\n**Condicional If/Else**");

        ScriptInterpreter interpreter3 = new ScriptInterpreter(trace);

        List<ScriptOp> conditionalScript = List.of(
            new OpN(1),
            new OpIf(),
            new OpN(10),
            new OpElse(),
            new OpN(5),
            new OpEndIf()
        );

        boolean result3 = interpreter3.execute(conditionalScript);
        System.out.println("Resultado final (condicional): " + result3);

    }
}