package nikiforovta.Split;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Splitter {
    static void byChars(String inputName, String outputName, int num, boolean numerate) throws IOException {
      File in = new File(inputName);
      int k = 0;
      for (int i = 0; i < in.length(); i += num) {
        k++;
        String input = i + num < in.length() ? in.toString().substring(i, i + num) :
                in.toString().substring(i, (int) in.length() - 1);
        Splitter.Writer(outputName, input, k, numerate);
      }
    }

    static void byStrings(String inputName, String outputName, int num, boolean numerate) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get(inputName), Charset.defaultCharset());
      int inputLines = 0;
      for (String ignored : lines) {
        inputLines++;
      }
      int k = 0;
      for (int i = 0; i < inputLines; i += num) {
        k++;
        StringBuilder inputL = new StringBuilder();
        if (i + num < inputLines) {
          for (int j = i - 1; j < i + num - 1; j++) {
                      inputL.append(lines.get(j));
          }
        } else {
          for (int j = 0; j < inputLines - 1; j++) {
            inputL.append(lines.get(j));
          }
        }
        String input = inputL.toString();
        Splitter.Writer(outputName, input, k, numerate);
      }
    }

    static void byFiles(String inputName, String outputName, int num, boolean numerate) throws IOException {
      File in = new File(inputName);
      int charForFile = (int) Math.ceil((double) in.length() / num);
      int k = 0;
      for (int i = 0; i < in.length(); i += charForFile) {
        k++;
        String input = i + charForFile < in.length() ? in.toString().substring(i, i + charForFile) :
                in.toString().substring(i, (int) in.length() - 1);
        Splitter.Writer(outputName, input, k, numerate);
      }
      if (k < num) {
        String input = in.toString().substring(k * charForFile, (int) in.length() - 1);
        Splitter.Writer(outputName, input, num, numerate);
      }
    }

    private static void Writer(String outputName, String input, int k, boolean numerate) throws IOException {
      char[] toChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
              'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
      if (numerate) {
        File file = new File(outputName + k);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
          bufferedWriter.write(input);
        bufferedWriter.close();
      } else {
        int i = 0;
        int j = 1;
        while (i + j < k) {
          j++;
          if (j == 27) {
            i++;
            j = 1;
          }
        }
        File file = new File(outputName + toChar[i] + toChar[j - 1]);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(input);
        bufferedWriter.close();
          }
        }
      }
