package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCaseShow {
	static music.PlayList p;

	@Test
	public void testSearchPos() {
		p = new music.PlayList("PlayList 2");
		music.Song a = new music.Song("Song1","Singer1",200);
		p.add(a);
		music.Song b = new music.Song("Song 2", "Singer 2", 10);
		p.add(b);
		a = new music.Song("Oh no","What a Singer",100);
		p.add(a);
		
		music.Song ms = p.search("Song 2");
		assertEquals(ms,b);
	}
	@Test
	public void testSearchNeg() {
		p = new music.PlayList("PlayList 2");
		music.Song a = new music.Song("Song1","Singer1",200);
		p.add(a);
		music.Song b = new music.Song("Song 2", "Singer 2", 10);
		p.add(b);
		
		music.Song ms = p.search("Song2");
		assertEquals(ms,null);
	}
}
