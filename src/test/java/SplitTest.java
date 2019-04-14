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
    assertFileContent("src/test/testData/outputaa.txt", "[abc]");
    assertFileContent("src/test/testData/outputab.txt", "[d]");
    new File("src/test/testData/outputaa.txt").delete();
    new File("src/test/testData/outputab.txt").delete();
    Splitter.byChars("src/test/testData/input2.txt", "src/test/testData/output", 2, true);
    assertFileContent("src/test/testData/output1.txt", "[ab]");
    assertFileContent("src/test/testData/output2.txt", "[cd]");
    new File("src/test/testData/output1.txt").delete();
    new File("src/test/testData/output2.txt").delete();
  }

  @Test
  public void byFiles() throws IOException {
    Splitter.byFiles("src/test/testData/input1.txt", "src/test/testData/output", 4, true);
    assertFileContent("src/test/testData/output1.txt", "[a]");
    assertFileContent("src/test/testData/output2.txt", "[b]");
    assertFileContent("src/test/testData/output3.txt", "[c]");
    assertFileContent("src/test/testData/output4.txt", "[d]");
    new File("src/test/testData/output1.txt").delete();
    new File("src/test/testData/output2.txt").delete();
    new File("src/test/testData/output3.txt").delete();
    new File("src/test/testData/output4.txt").delete();
    Splitter.byFiles("src/test/testData/input2.txt", "src/test/testData/output", 2, false);
    assertFileContent("src/test/testData/outputaa.txt", "[ab]");
    assertFileContent("src/test/testData/outputab.txt", "[cd]");
    new File("src/test/testData/outputaa.txt").delete();
    new File("src/test/testData/outputab.txt").delete();
  }

  @Test
  public void byLines() throws IOException {
    Splitter.byLines("src/test/testData/input1.txt", "src/test/testData/output", 2, false);
    assertFileContent("src/test/testData/outputaa.txt", "[abcd]");
    new File("src/test/testData/outputaa.txt").delete();
    Splitter.byLines("src/test/testData/input2.txt", "src/test/testData/output", 3, true);
    assertFileContent("src/test/testData/output1.txt", "[a, b, c]");
    assertFileContent("src/test/testData/output2.txt", "[d]");
    new File("src/test/testData/output1.txt").delete();
    new File("src/test/testData/output2.txt").delete();
  }
}