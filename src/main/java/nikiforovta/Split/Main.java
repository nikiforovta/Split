package nikiforovta.Split;

import java.io.IOException;

class Main {
  private static String outputName = "x";
  private static boolean numerate = false;
  private static int numOfString = 100;
  private static int numOfChars = 0;
  private static int numOfFiles = 0;

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println("file     | имя входного файла\n" +
              "-o ofile | базовое имя выходного файла\n" +
              "-d       | нумерация выходных файлов\n" +
              "-l num   | указать размер выходных файлов в строчках. " +
              "По умолчанию выходные файлы имеют размер в 100 строчек.\n" +
              "-с num   | указать размер выходных файлах в символах.\n" +
              "-n num   | указать количество выходных файлов.\n" +
              "split [-d] [-l num|-c num|-n num] [-o ofile] file");
    } else {
      boolean isFound = false;
      if (args[0].equals("-d")) numerate = true;
      String inputName = args[args.length - 1];
      for (int i = 0; i < args.length - 1 ; i++) {
        if (args[i].equals("-l")) {
          if (isFound) throw new IllegalArgumentException();
          numOfString = Integer.parseInt(args[i+1]);
          isFound = true;
        }
        if (args[i].equals("-c")) {
          if (isFound) throw new IllegalArgumentException();
           numOfChars = Integer.parseInt(args[i+1]);
          isFound = true;
        }
        if (args[i].equals("-n")) {
          if (isFound) throw new IllegalArgumentException();
          numOfFiles = Integer.parseInt(args[i+1]);
          isFound = true;
        }
        if (args[i].equals("-o")) {
          if (args[i+1].equals("-")) outputName = inputName;
          else outputName = args[i+1];
        }
      }
      if (numOfFiles != 0 || numOfChars != 0) {
        if (numOfChars != 0) Splitter.byChars(inputName, outputName, numOfChars, numerate);
        if (numOfFiles != 0) Splitter.byFiles(inputName, outputName, numOfChars, numerate);
      } else {
        Splitter.byLines(inputName, outputName, numOfString, numerate);
      }
    }
  }
}