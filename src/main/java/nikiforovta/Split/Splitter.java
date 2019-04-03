package nikiforovta.Split;
 import java.io.*;
 import java.nio.charset.Charset;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.*;

 public class Splitter {
   public static void byChars(String inputName, String outputName, int num, boolean numerate) throws IOException {
     String in = getString(inputName);
     splittingByChars(outputName, numerate, in, num);
   }

   public static void byStrings(String inputName, String outputName, int num, boolean numerate) throws IOException {
     List<String> lines = Files.readAllLines(Paths.get(inputName), Charset.defaultCharset());
       int inputLines = lines.size();
       int k = 0;
       for (int i = 0; i < inputLines; i += num) {
         k++;
         StringBuilder inputL = new StringBuilder();
         if (i + num < inputLines) {
           for (int j = i; j <= i + num - 2; j++) {
             inputL.append(lines.get(j)).append("\n");
           }
           inputL.append(lines.get(i + num - 1));
         } else {
           for (int j = i; j <= inputLines - 2; j++) {
             inputL.append(lines.get(j)).append("\n");
           }
           inputL.append(lines.get(inputLines - 1));
         }
         String input = inputL.toString();
         Splitter.Writer(outputName, input, k, numerate);
       }
     }

   public static void byFiles(String inputName, String outputName, int num, boolean numerate) throws IOException {
     String in = getString(inputName);
     int charForFile = (int) Math.ceil((double) in.length() / num);
     splittingByChars(outputName, numerate, in, charForFile);
     int k = 0;
     for (int i = 0; i < in.length(); i += charForFile) k++;
     if (k < num) {
         String input = in.substring(k * charForFile, in.length() - 1);
         Splitter.Writer(outputName, input, num, numerate);
       }
     }

   private static void splittingByChars(String outputName, boolean numerate, String in, int charForFile) throws IOException {
     int k = 0;
     for (int i = 0; i < in.length(); i += charForFile) {
       k++;
       String input = i + charForFile - 1 <= in.length() ? in.substring(i, i + charForFile): in.substring(i);
       Splitter.Writer(outputName, input, k, numerate);
     }
   }

   private static String getString(String inputName) throws IOException {
     List<String> lines = Files.readAllLines(Paths.get(inputName), Charset.defaultCharset());
     StringBuilder inp = new StringBuilder();
     for (String line : lines) inp.append(line);
     return inp.toString();
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
         while (i * 26 + j < k) {
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