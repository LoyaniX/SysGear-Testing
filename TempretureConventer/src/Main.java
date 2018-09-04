import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int Temp, KelvinTemp, CelsiusTemp, FarenheitTemp;
        while (true) {
            String inputData = reader.readLine();
            Temp = Integer.valueOf(inputData.replaceAll("\\D+",""));
            if (inputData.endsWith("C")) {
                KelvinTemp = Temp + 273;
                FarenheitTemp = (int)(1.8f * Temp + 32);
                System.out.println("{ \"K\": \"" +  KelvinTemp + "\", \"F:\" \"" + FarenheitTemp + "\"}");
            } else if (inputData.endsWith("K")) {
                FarenheitTemp = (int)(1.8f * (Temp - 273) + 32);
                CelsiusTemp = Temp - 273;
                System.out.println("{ \"F\": \"" +  FarenheitTemp + "\", \"C:\" \"" + CelsiusTemp + "\"}");
            } else if (inputData.endsWith("F")) {
                CelsiusTemp = (int)((Temp - 32) * 5f/9f);
                KelvinTemp = CelsiusTemp + 273;
                System.out.println("{ \"C\": \"" +  CelsiusTemp + "\", \"K:\" \"" + KelvinTemp + "\"}");
            }
        }
    }
}
