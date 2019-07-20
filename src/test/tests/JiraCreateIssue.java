package tests;

import data.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.ReusableMethods.jiraCreateIssue;

public class JiraCreateIssue extends TestBase {

    @Test
    public void jiraCreateIssueTest() throws IOException {
        jiraCreateIssue();
    }
}
