import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    boolean numerate = false;
    int lengthOut = 0;
    int lineOut = 100;
    int fileOut = 0;
    String outputName = "";
    String inputName = "";
    String outputFile = "";


    Scanner scan = new Scanner(System.in);
    if (!scan.next().equals("split")) throw new IllegalArgumentException();
    if (scan.next().equals("-d")) numerate = true;
    String splitParam = scan.next();
    if (splitParam.equals("-c") || splitParam.equals("-n") || splitParam.equals("-l")) {
      int splitQuant = scan.hasNextInt() ? scan.nextInt() : -1;
      if (splitQuant == -1) throw new IllegalArgumentException("Неверно введено количество");
      if (splitParam.equals("-c")) {
        lengthOut = splitQuant;
      }
      if (splitParam.equals("-l")) lineOut = splitQuant;

      if (splitParam.equals("-n")) fileOut = splitQuant;
    } else {
      if (splitParam.equals("-o")) outputFile = scan.next();
      else {
        String lastParam = scan.next();
        if (lastParam.equals("-o")) {
          outputFile = scan.next();
        } else {
          outputFile = "x";
        }
      }
      inputName = scan.hasNext() ? scan.next() : null;
      if (inputName == null) throw new IllegalArgumentException("Неверно введены данные"); }
      if (outputFile.equals("-")) outputName = inputName;
      if (fileOut > 0) {
        Main.byFiles(inputName, outputName, fileOut, numerate);
      } else {
        if (lengthOut > 0) {
          Main.byLength(inputName, outputName, lengthOut, numerate);
        } else {
      if (lineOut > 0) {
        Main.byLines(inputName, outputName, lineOut, numerate);
      }
        }
      }
  }

    public static void byFiles(String input, String output, int quant, boolean num) {

    }

    public static void byLength(String input, String output, int quant, boolean num) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get(input), Charset.defaultCharset());
      int inputChars = 0;
      for (String x : lines) { inputChars += x.length(); }
      for (int i = 0; i < inputChars; i += quant) {
        for (int k = 0; k < quant; k++) {

        }
    }
  }

    public static void byLines(String input, String output, int quant, boolean num) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(input), Charset.defaultCharset());
        int inputLines = 0;
        for (String ignored : lines) {
          inputLines++;
        }
      for (int i = 0; i < inputLines; i += quant) {

      }
    }
  }
