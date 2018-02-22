package br.com.abevieiramota.simplenlg;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

@RunWith(JUnit4.class)
public class HelloWorld {

	private static Lexicon lexicon;
	private static NLGFactory nlgFactory;
	private static Realiser realiser;

	@BeforeClass
	public static void beforeClass() {
		lexicon = Lexicon.getDefaultLexicon();
		nlgFactory = new NLGFactory(lexicon);
		realiser = new Realiser(lexicon);
	}

	/**
	 * canned text: string wich we'd like output to the screen as is
	 */
	@Test
	public void cannedText() {
		
		String input = "my dog is happy";
		String expectedOutput = "My dog is happy.";

		NLGElement s1 = nlgFactory.createSentence(input);
		
		assertEquals(expectedOutput, realiser.realiseSentence(s1));
	}
	
	@Test
	public void multipleCannedText() {
		String firstText = "my dog is happy";
		String secondText = "my cat is sad";
		
		NLGElement s1 = nlgFactory.createSentence(firstText);
		NLGElement s2 = nlgFactory.createSentence(secondText);
		DocumentElement de = nlgFactory.createSentence(Arrays.asList(s1, s2));
		
		System.out.println(realiser.realiseSentence(de));
	}
	
	@Test
	public void oiMundo() {
		
		SPhraseSpec clause = nlgFactory.createClause();
		clause.setSubject("Abelardo");
		clause.setVerb("is");
		clause.setObject("hard");
		
		System.out.println(realiser.realiseSentence(clause));
	}
}
