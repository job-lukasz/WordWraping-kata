package main;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WordWrapingTest {
	Wrap wrapper;
  @BeforeTest
  public void initTests(){
	  wrapper = new Wrap();
	  wrapper.setMaxWidth(24);
	  wrapper.setMinWidth(18);
  }
  @Test
  public void EmptyText() {
	  String rawText = "";
	  Assert.assertEquals(wrapper.wrapText(rawText), rawText);
  }
  
  @Test
  public void LessSignsThenColumnMaxWidth() {
	  String rawText = "jakis krotki tekst";
	  Assert.assertEquals(wrapper.wrapText(rawText), rawText);
  }
  
  @Test
  public void LessSignsThenColumnMinWidth() {
	  String rawText = "jakis krotki tekst";
	  Assert.assertEquals(wrapper.wrapText(rawText), rawText);
  }
  
  @Test
  public void MoreSignsThenColumnMaxWidthNoSpaces() {
	  wrapper.setMaxWidth(3);
	  wrapper.setMinWidth(1);
	  String rawText = "baabcac";
	  Assert.assertEquals(wrapper.wrapText(rawText), "ba-\nab-\ncac");
  }
  
  @Test
  public void MoreSignsThenColumnMaxWidthWithSpaces() {
	  wrapper.setMaxWidth(3);
	  wrapper.setMinWidth(1);
	  String rawText = "baa bca cgh";
	  Assert.assertEquals(wrapper.wrapText(rawText), "baa\nbca\ncgh");
  }
  
  @Test
  public void MoreSignsThenColumnMaxWidthWithSpacesWordBiggerThemMinLength() {
	  wrapper.setMaxWidth(12);
	  wrapper.setMinWidth(10);
	  String rawText = "baa bca ssddc";
	  Assert.assertEquals(wrapper.wrapText(rawText), "baa bca ssd-\ndc");
  }
  
  @Test
  public void MoreSignsThenColumnMaxWidthWithSpacesWordSmallerThemMinLength() {
	  wrapper.setMaxWidth(12);
	  wrapper.setMinWidth(4);
	  String rawText = "baa bca ssddc";
	  Assert.assertEquals(wrapper.wrapText(rawText), "baa bca\nssddc");
  }
}
