package tests;

import data.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.ReusableMethods.jiraUpdateComment;

public class JiraCreateComment extends TestBase {

    @Test
    public void jiraCreateCommentTest() throws IOException {
//        jiraCreateComment();
//        jiraSearchCommentId();
        jiraUpdateComment();

    }
}
