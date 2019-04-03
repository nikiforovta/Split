import nikiforovta.Split.Splitter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class SplitTest {

  private void assertFileContent(String name, String expectedContent) throws IOException {
    String content = Files.readAllLines(Paths.get(name), Charset.defaultCharset()).toString();
    assertEquals(expectedContent, content);
  }

  @Test
  public void byChars() throws IOException {
    Splitter.byChars("src/test/testData/input1.txt", "src/test/testData/output", 3, false);
    assertFileContent("src/test/testData/outputaa", "[abc]");
    assertFileContent("src/test/testData/outputab", "[d]");
    new File("src/test/testData/outputaa").delete();
    new File("src/test/testData/outputab").delete();
    Splitter.byChars("src/test/testData/input2.txt", "src/test/testData/output", 2, true);
    assertFileContent("src/test/testData/output1", "[ab]");
    assertFileContent("src/test/testData/output2", "[cd]");
    new File("src/test/testData/output1").delete();
    new File("src/test/testData/output2").delete();
  }

  @Test
  public void byFiles() throws IOException {
    Splitter.byFiles("src/test/testData/input1.txt", "src/test/testData/output", 4, true);
    assertFileContent("src/test/testData/output1", "[a]");
    assertFileContent("src/test/testData/output2", "[b]");
    assertFileContent("src/test/testData/output3", "[c]");
    assertFileContent("src/test/testData/output4", "[d]");
    new File("src/test/testData/output1").delete();
    new File("src/test/testData/output2").delete();
    new File("src/test/testData/output3").delete();
    new File("src/test/testData/output4").delete();
    Splitter.byFiles("src/test/testData/input2.txt", "src/test/testData/output", 2, false);
    assertFileContent("src/test/testData/outputaa", "[ab]");
    assertFileContent("src/test/testData/outputab", "[cd]");
    new File("src/test/testData/outputaa").delete();
    new File("src/test/testData/outputab").delete();
  }

  @Test
  public void byStrings() throws IOException {
    Splitter.byStrings("src/test/testData/input1.txt", "src/test/testData/output", 2, false);
    assertFileContent("src/test/testData/outputaa", "[abcd]");
    new File("src/test/testData/outputaa").delete();
    Splitter.byStrings("src/test/testData/input2.txt", "src/test/testData/output", 3, true);
    assertFileContent("src/test/testData/output1", "[a, b, c]");
    assertFileContent("src/test/testData/output2", "[d]");
    new File("src/test/testData/output1").delete();
    new File("src/test/testData/output2").delete();

  }
}
